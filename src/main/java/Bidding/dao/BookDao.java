package Bidding.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bidding.Auction_Transaction;
import Bidding.Auction_Type;
import Bidding.Bid;
import Bidding.Book;
import Bidding.Category;
import Bidding.Feedback;
import Bidding.User;

public class BookDao {

	private static Connection connection;
	private Book book;
	private Statement st;
	private ResultSet rs;
	private UserDao userDao = new UserDao();
	
	public BookDao() {
		connection = DatabaseConnection.getConnection();
	}
	
	public List<Book> getAllBooks() {
		String query = "select * from book, auction_type where book.book_id = auction_type.book_id ";
		List<Book> books = new ArrayList<Book>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Book book = new Book();
				book.setBook_id(rs.getString("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setCondition(rs.getString("book_condition"));
				book.setStatus(rs.getString("status"));
				book.setiPrice(rs.getInt("iPrice"));
				book.setfPrice(rs.getInt("fPrice"));
				book.setoPrice(rs.getInt("oPrice"));
				book.setIsbn(rs.getString("isbn"));
				book.setD_id(rs.getString("d_id"));
				book.setU_id(rs.getString("u_id"));
				book.setC_id(rs.getString("c_id"));
				book.setBid_category(rs.getString("type_name"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return books;
	 }
	
	public Book getBook(String bookid) {
		String query = "select * from book, auction_type where book.book_id = '" + bookid + "' and book.book_id = auction_type.book_id ";
		Book book = new Book();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				book.setBook_id(rs.getString("book_id"));
				book.setBook_name(rs.getString("book_name"));
				book.setAuthor(rs.getString("author"));
				book.setCondition(rs.getString("book_condition"));
				book.setStatus(rs.getString("status"));
				book.setiPrice(rs.getInt("iPrice"));
				book.setfPrice(rs.getInt("fPrice"));
				book.setoPrice(rs.getInt("oPrice"));
				book.setIsbn(rs.getString("isbn"));
				book.setD_id(rs.getString("d_id"));
				book.setU_id(rs.getString("u_id"));
				book.setC_id(rs.getString("c_id"));
				book.setBid_category(rs.getString("type_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return book;
	 }
	
	public int getAuctionID(int bidid) {
		int auctionid = -1;
		String query = "select auction_id from auction_transaction where bid_id = " + bidid;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				auctionid = rs.getInt("auction_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auctionid;
	}
	
	public int generateShipping(Bid selected_bid, int auction_id) {
		int shippingid = 0;
		
		String query = "insert into shipping(delivery_address, return_address, status, arrival_date, auction_id) values(?,?,?,?,?)";
		
		User to = userDao.getUserbyID(selected_bid.getBidder_id());
		
		java.util.Date today = new java.util.Date();
		Date arrival_date = new Date(today.getYear(), today.getMonth(), today.getDate() + 10);
		System.out.println("Estimated arrival date = " + arrival_date);
		System.out.println("Today = " + today.getDay());
		int res = -1;
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, to.getAddress());
			pst.setString(2, "126 park Ave, Bridgeport, CT 06604");
			pst.setString(3, "In Progress");
			pst.setDate(4, arrival_date);
			pst.setInt(5, auction_id);
			
			res = pst.executeUpdate();
			
			if(res > 0) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return shippingid;
	}
	
	public void updateBookDetails(String book_id, int price) {
		String query = "update book set status='auctioned', fPrice="+price+" where book_id='" + book_id + "'";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateReturnDate(String book_id, Date returndate) {
		String query = "update auction_type set due_date='" +returndate+ "' where book_id = '" + book_id + "'";
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int auction(int bid_id) {
		Bid selected_bid = getBid(String.valueOf(bid_id));
		String query = "insert into auction_transaction(payment_mode, transaction_date, bid_id) values(?,?,?)";
		java.util.Date today = new java.util.Date();
		Date trans_date = new Date(today.getYear(), today.getMonth(), today.getDate());
		
		int val = userDao.updateBalance(selected_bid.getBidder_id(), selected_bid.getOffer_price());
		System.out.println("Balance checked: " + val);
		int res = -1;
		if(val > 0) {
			Book book = getBook(selected_bid.getBook_id());
			userDao.addCredits(book.getU_id(), selected_bid.getOffer_price());
			if(book != null && ("renting").equals(book.getBid_category())) {
				Date returndate = new Date(today.getYear(), today.getMonth() + 6, today.getDate());
				updateReturnDate(book.getBook_id(), returndate);
			}
			try {
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, "online");
				pst.setDate(2, trans_date);
				pst.setInt(3, selected_bid.getBid_id());
				res = pst.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
			int auction_id = getAuctionID(bid_id); 
			generateShipping(selected_bid, auction_id);
			updateBookDetails(selected_bid.getBook_id(), selected_bid.getOffer_price());
			
			return res;
		}
		return -1;
		
	}
	
	public Bid getBid(String bid_id) {
		String query = "select * from bid, book, auction_type where bid.bid_id = '" + bid_id + "' and book.book_id = bid.book_id and book.book_id = auction_type.book_id";
		Bid bid = new Bid();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				bid.setBid_id(rs.getInt("bid_id"));
				bid.setBidder_id(rs.getString("bid.u_id"));
				bid.setBook_id(rs.getString("book_id"));
				bid.setOffer_price(rs.getInt("offer_price"));
				bid.setBook_status(rs.getString("status"));
				bid.setBook_name(rs.getString("book_name"));
				bid.setCat_type(rs.getString("type_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return bid;
	}
	
	
	public int placeBid(Bid bid) {
		String query = "insert into bid(book_id, u_id, offer_price) values(?,?,?)";
		
		int res = -1;
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, bid.getBook_id());
			pst.setString(2, bid.getBidder_id());
			pst.setInt(3, bid.getOffer_price());
			
			res = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return res;
	}
	
	public List<Bid> getAllBids(User user) {
		String query = "select * from bid, book, auction_type where bid.u_id = '" + user.getUserid() + "' and book.book_id = bid.book_id and book.book_id = auction_type.book_id";
		List<Bid> bids = new ArrayList<>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setBid_id(rs.getInt("bid_id"));
				bid.setBidder_id(rs.getString("bid.u_id"));
				bid.setBook_id(rs.getString("book_id"));
				bid.setOffer_price(rs.getInt("offer_price"));
				bid.setBook_status(rs.getString("status"));
				bid.setBook_name(rs.getString("book_name"));
				bid.setCat_type(rs.getString("type_name"));
				bids.add(bid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return bids;
	 }
	
	public List<Bid> getMyBooksBids(User user, String bookid) {
		String query = "select * from book, bid, auction_type where book.u_id = '" + user.getUserid() + "' and book.book_id = '" + bookid + "' and book.book_id = bid.book_id and book.book_id = auction_type.book_id";
		List<Bid> bids = new ArrayList<>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Bid bid = new Bid();
				bid.setBid_id(rs.getInt("bid_id"));
				bid.setBidder_id(rs.getString("bid.u_id"));
				bid.setBook_id(rs.getString("book_id"));
				bid.setOffer_price(rs.getInt("offer_price"));
				bid.setBook_status(rs.getString("status"));
				bid.setBook_name(rs.getString("book_name"));
				bid.setCat_type(rs.getString("type_name"));
				bids.add(bid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return bids;
	 }
	
	public int addNewBook(Book book, Auction_Type at) {
		int res = -1;
		String query = "insert into book values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, book.getBook_id());
			pst.setString(2, book.getBook_name());
			pst.setString(3, book.getAuthor());
			pst.setString(4, book.getIsbn());
			pst.setString(5, book.getStatus());
			pst.setString(9, book.getCondition());
			pst.setString(10, book.getD_id());
			pst.setString(11, book.getU_id());
			pst.setString(12, book.getC_id());
			pst.setInt(6, book.getiPrice());
			pst.setInt(7, book.getoPrice());
			pst.setInt(8, 0);
			
			res = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res > 0) {
			res = setAuction_Type(at);
		}
		 
		return res;
	}
	
	public int setAuction_Type(Auction_Type at) {
		int res = -1;
		String query = "insert into auction_type(type_name, book_id, c_id) values(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, at.getType_name());
			pst.setString(2, at.getBook_id());
			pst.setString(3, at.getCat_id());
			
			res = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return res;
	}
	
	public List<Category> getCategory() {
		String query = "select * from category";
		List<Category> categories = new ArrayList<>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Category cat = new Category();
				cat.setAuction_status(rs.getString("auction_status"));
				cat.setCat_id(rs.getString("cat_id"));
				cat.setCat_type(rs.getString("cat_type"));
				categories.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		return categories;
	}
	
	public int updateAuctionType(String status, String cat_id) {
		int res = -1;
		String query = "update auction_type set type_name='" + status + "' where c_id = '" + cat_id + "'";
		try {
			Statement st = connection.createStatement();
			res = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public int updateCategory(String status, String cat_id) {
		int res = -1;
		String query = "update category set auction_status='" + status + "' where cat_id = '" + cat_id + "'";
		try {
			Statement st = connection.createStatement();
			res = st.executeUpdate(query);
			res = updateAuctionType(status, cat_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Auction_Transaction> getAuction(String userid) {
		List<Auction_Transaction> res = new ArrayList<>();
		
		String query = "select * from auction_transaction as at, bid, shipping where bid.u_id = '" + userid + "' and at.bid_id = bid.bid_id and shipping.auction_id = at.auction_id";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Auction_Transaction trans = new Auction_Transaction();
				trans.setAuction_id(rs.getString("at.auction_id"));
				trans.setBid_id(rs.getString("bid.bid_id"));
				trans.setShipping_id(rs.getString("shipping_id"));
				trans.setShipping_status(rs.getString("shipping.status"));
				trans.setBookid(rs.getString("book_id"));
				trans.setPayment_mode(rs.getString("payment_mode"));
				trans.setPrice(rs.getInt("offer_price"));
				trans.setTransaction_date(rs.getDate("transaction_date"));
				res.add(trans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Auction_Transaction> getMyBookAuction(String userid) {
		List<Auction_Transaction> res = new ArrayList<>();
		
		String query = "select * from auction_transaction as at, bid, book where book.u_id = '" + userid + "' and book.status = 'auctioned' and book.book_id = bid.book_id and at.bid_id = bid.bid_id";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				Auction_Transaction trans = new Auction_Transaction();
				trans.setAuction_id(rs.getString("at.auction_id"));
				trans.setBid_id(rs.getString("bid.bid_id"));
				trans.setBookid(rs.getString("book_id"));
				trans.setPayment_mode(rs.getString("payment_mode"));
				trans.setPrice(rs.getInt("offer_price"));
				trans.setTransaction_date(rs.getDate("transaction_date"));
				trans.setBuyerid(rs.getString("bid.u_id"));
				res.add(trans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String getSellerID(int auctionid) {
		String query = "select * from bid, auction_transaction as at, book where at.auction_id = " + auctionid + " and bid.bid_id = at.bid_id and book.book_id = bid.book_id";
		String id = "";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				id = rs.getString("book.u_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int updateUserFeedback(String userid, int feedback) {
		int res = -1;
		String query = "update user set rating=" + feedback + " where user_id = '" + userid + "'";
		try {
			Statement st = connection.createStatement();
			res = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(res == 1) {
			System.out.println("Updated seller feedback for = " + userid);
		} else {
			System.out.println("NOT updated seller feedback for = " + userid);
		}
		
		return res;
	}
	
	public int addFeedback(Feedback feedback) {
		int res = -1;
		String query = "insert into feedback values(?,?,?)";
		String sellerid = getSellerID(feedback.getAuction_id());
		
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, feedback.getAuction_id());
			pst.setString(2, sellerid);
			pst.setInt(3, feedback.getSeller_rate());
			
			res = pst.executeUpdate();
			updateUserFeedback(sellerid, feedback.getSeller_rate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public String getBuyerID(int auctionid) {
		String query = "select * from bid, auction_transaction as at where at.auction_id = " + auctionid + " and bid.bid_id = at.bid_id";
		String id = "";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				id = rs.getString("bid.u_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int addBuyerFeedback(Feedback feedback) {
		int res = -1;
		String query = "insert into feedback values(?,?,?)";
		String buyerid = getBuyerID(feedback.getAuction_id());
		
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, feedback.getAuction_id());
			pst.setString(2, buyerid);
			pst.setInt(3, feedback.getSeller_rate());
			
			res = pst.executeUpdate();
			updateUserFeedback(buyerid, feedback.getSeller_rate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public List<List<Object>> getReport(String type, String bookid) {
		String query = "";
		if(type.equals("auction")) {
			query = "select b.book_id, b.book_name, b.oPrice, b.fPrice, at.auction_id, at.transaction_date, sh.status from book as b, bid, auction_transaction as at, shipping as sh where b.book_id = bid.book_id and bid.bid_id = at.bid_id and at.auction_id = sh.auction_id";
		} else {
			query = "select b.book_id, b.book_name, b.oPrice, b.fPrice, bid.offer_price, at.transaction_date, sh.status from book as b, bid, auction_transaction as at, shipping as sh where b.book_id = '"+ bookid +"' and b.book_id = bid.book_id and bid.bid_id = at.bid_id and at.auction_id = sh.auction_id";
		}
		Statement st;
		ResultSet rs;
		ResultSetMetaData metadata;
		List<List<Object>> row = new ArrayList<List<Object>>();
		List<Object> colname = new ArrayList<Object>();
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			metadata = rs.getMetaData();
			for(int i = 1; i <= metadata.getColumnCount(); i++) {
				colname.add(metadata.getColumnName(i));
			}
			row.add(colname);
			while(rs.next()) {
				List<Object> data = new ArrayList<Object>();
				for(int i = 1; i <= metadata.getColumnCount(); i++) {
					data.add(rs.getObject(i));
				}
				row.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
}

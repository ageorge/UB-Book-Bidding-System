package Bidding;

import java.sql.Date;

public class Auction_Transaction {
	private String auction_id;
	private String payment_mode;
	private Date transaction_date;
	private String bid_id;
	private String shipping_id;
	private String shipping_status;
	private int price;
	private String bookid;
	private String buyerid;
	
	/**
	 * @return the buyerid
	 */
	public String getBuyerid() {
		return buyerid;
	}
	/**
	 * @param buyerid the buyerid to set
	 */
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	/**
	 * @return the shipping_status
	 */
	public String getShipping_status() {
		return shipping_status;
	}
	/**
	 * @param shipping_status the shipping_status to set
	 */
	public void setShipping_status(String shipping_status) {
		this.shipping_status = shipping_status;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the bookid
	 */
	public String getBookid() {
		return bookid;
	}
	/**
	 * @param bookid the bookid to set
	 */
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	/**
	 * @return the auction_id
	 */
	public String getAuction_id() {
		return auction_id;
	}
	/**
	 * @param auction_id the auction_id to set
	 */
	public void setAuction_id(String auction_id) {
		this.auction_id = auction_id;
	}
	/**
	 * @return the payment_mode
	 */
	public String getPayment_mode() {
		return payment_mode;
	}
	/**
	 * @param payment_mode the payment_mode to set
	 */
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
	}
	/**
	 * @return the transaction_date
	 */
	public Date getTransaction_date() {
		return transaction_date;
	}
	/**
	 * @param transaction_date the transaction_date to set
	 */
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	/**
	 * @return the bid_id
	 */
	public String getBid_id() {
		return bid_id;
	}
	/**
	 * @param bid_id the bid_id to set
	 */
	public void setBid_id(String bid_id) {
		this.bid_id = bid_id;
	}
	/**
	 * @return the shipping_id
	 */
	public String getShipping_id() {
		return shipping_id;
	}
	/**
	 * @param shipping_id the shipping_id to set
	 */
	public void setShipping_id(String shipping_id) {
		this.shipping_id = shipping_id;
	}
	
	
}

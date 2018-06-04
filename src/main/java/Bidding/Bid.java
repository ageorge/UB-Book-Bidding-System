package Bidding;

public class Bid {
	private int bid_id;
	private String book_id;
	private String book_name;
	private String bidder_id;
	private int offer_price;
	private String book_status;
	private String cat_type;
	
	
	/**
	 * @return the book_name
	 */
	public String getBook_name() {
		return book_name;
	}
	/**
	 * @param book_name the book_name to set
	 */
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	/**
	 * @return the cat_type
	 */
	public String getCat_type() {
		return cat_type;
	}
	/**
	 * @param cat_type the cat_type to set
	 */
	public void setCat_type(String cat_type) {
		this.cat_type = cat_type;
	}
	/**
	 * @return the book_status
	 */
	public String getBook_status() {
		return book_status;
	}
	/**
	 * @param book_status the book_status to set
	 */
	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}
	/**
	 * @return the bid_id
	 */
	public int getBid_id() {
		return bid_id;
	}
	/**
	 * @param bid_id the bid_id to set
	 */
	public void setBid_id(int bid_id) {
		this.bid_id = bid_id;
	}
	/**
	 * @return the book_id
	 */
	public String getBook_id() {
		return book_id;
	}
	/**
	 * @param book_id the book_id to set
	 */
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	/**
	 * @return the bidder_id
	 */
	public String getBidder_id() {
		return bidder_id;
	}
	/**
	 * @param bidder_id the bidder_id to set
	 */
	public void setBidder_id(String bidder_id) {
		this.bidder_id = bidder_id;
	}
	/**
	 * @return the offer_price
	 */
	public int getOffer_price() {
		return offer_price;
	}
	/**
	 * @param offer_price the offer_price to set
	 */
	public void setOffer_price(int offer_price) {
		this.offer_price = offer_price;
	}
	
	
}

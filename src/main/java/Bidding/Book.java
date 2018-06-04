package Bidding;

public class Book {
	private String book_id;
	private String book_name;
	private String author;
	private String isbn;
	private String status;
	private int iPrice;
	private int fPrice;
	private int oPrice;
	private String condition;
	private String d_id;
	private String u_id;
	private String c_id;
	private String bid_category;
	
	
	
	/**
	 * @return the bid_category
	 */
	public String getBid_category() {
		return bid_category;
	}
	/**
	 * @param bid_category the bid_category to set
	 */
	public void setBid_category(String bid_category) {
		this.bid_category = bid_category;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the iPrice
	 */
	public int getiPrice() {
		return iPrice;
	}
	/**
	 * @param iPrice the iPrice to set
	 */
	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}
	/**
	 * @return the fPrice
	 */
	public int getfPrice() {
		return fPrice;
	}
	/**
	 * @param fPrice the fPrice to set
	 */
	public void setfPrice(int fPrice) {
		this.fPrice = fPrice;
	}
	/**
	 * @return the oPrice
	 */
	public int getoPrice() {
		return oPrice;
	}
	/**
	 * @param oPrice the oPrice to set
	 */
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the d_id
	 */
	public String getD_id() {
		return d_id;
	}
	/**
	 * @param d_id the d_id to set
	 */
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	/**
	 * @return the u_id
	 */
	public String getU_id() {
		return u_id;
	}
	/**
	 * @param u_id the u_id to set
	 */
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	/**
	 * @return the i_id
	 */
	public String getC_id() {
		return c_id;
	}
	/**
	 * @param i_id the i_id to set
	 */
	public void setC_id(String C_id) {
		this.c_id = C_id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return book_name;
	}
}

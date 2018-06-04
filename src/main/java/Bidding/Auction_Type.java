package Bidding;

import java.sql.Date;

public class Auction_Type {
	private int type_id;
	private Date due_date;
	private String type_name;
	private String book_id;
	private String cat_id;
	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}
	/**
	 * @param type_id the type_id to set
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	/**
	 * @return the due_date
	 */
	public Date getDue_date() {
		return due_date;
	}
	/**
	 * @param due_date the due_date to set
	 */
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	/**
	 * @return the type_name
	 */
	public String getType_name() {
		return type_name;
	}
	/**
	 * @param type_name the type_name to set
	 */
	public void setType_name() {
		if(cat_id.equals("C103") || cat_id.equals("C104") || cat_id.equals("C105") )
			type_name = "renting";
		else 
			type_name = "selling";
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
	 * @return the cat_id
	 */
	public String getCat_id() {
		return cat_id;
	}
	/**
	 * @param cat_id the cat_id to set
	 */
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	
	
}

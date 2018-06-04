package Bidding;

import java.sql.Date;

public class User {
	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_SELLER = "seller";
	public static final String ROLE_BIDDER = "bidder";
	
	private String userid;
	private String username;
	private String ssn;
	private String password;
	private Date dob;
	private String role;
	private float acc_bal;
	private String address;
	private int rating;
	
	
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return username;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.username = name;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the acc_bal
	 */
	public float getAcc_bal() {
		return acc_bal;
	}
	/**
	 * @param acc_bal the acc_bal to set
	 */
	public void setAcc_bal(float acc_bal) {
		this.acc_bal = acc_bal;
	}
	
}

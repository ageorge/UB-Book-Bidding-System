package Bidding;

public class User {
	private String name;
	private String ssn;
	/**
	 * @param name
	 * @param ssn
	 */
	public User(String name, String ssn) {
		super();
		this.name = name;
		this.ssn = ssn;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	
	
}

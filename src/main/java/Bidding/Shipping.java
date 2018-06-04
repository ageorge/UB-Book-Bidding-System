package Bidding;

import java.sql.Date;

public class Shipping {
	private String shipping_id;
	private String delivery_address;
	private String return_address;
	private String shipping_status;
	private Date arrival_date;
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
	/**
	 * @return the delivery_address
	 */
	public String getDelivery_address() {
		return delivery_address;
	}
	/**
	 * @param delivery_address the delivery_address to set
	 */
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	/**
	 * @return the return_address
	 */
	public String getReturn_address() {
		return return_address;
	}
	/**
	 * @param return_address the return_address to set
	 */
	public void setReturn_address(String return_address) {
		this.return_address = return_address;
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
	 * @return the arrival_date
	 */
	public Date getArrival_date() {
		return arrival_date;
	}
	/**
	 * @param arrival_date the arrival_date to set
	 */
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	
}

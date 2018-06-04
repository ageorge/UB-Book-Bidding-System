package Bidding;

public class Message {
	private int message_id;
	private String sender_id;
	private String receiver_id;
	private String subject;
	private String message_msg;
	/**
	 * @return the message_id
	 */
	public int getMessage_id() {
		return message_id;
	}
	/**
	 * @param message_id the message_id to set
	 */
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	/**
	 * @return the sender_id
	 */
	public String getSender_id() {
		return sender_id;
	}
	/**
	 * @param sender_id the sender_id to set
	 */
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	/**
	 * @return the receiver_id
	 */
	public String getReceiver_id() {
		return receiver_id;
	}
	/**
	 * @param receiver_id the receiver_id to set
	 */
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the message_msg
	 */
	public String getMessage_msg() {
		return message_msg;
	}
	/**
	 * @param message_msg the message_msg to set
	 */
	public void setMessage_msg(String message_msg) {
		this.message_msg = message_msg;
	}
	
}

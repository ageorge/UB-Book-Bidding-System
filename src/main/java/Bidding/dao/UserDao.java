package Bidding.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bidding.Department;
import Bidding.Message;
import Bidding.User;

public class UserDao {
	
	private static Connection connection;
	private User user;
	private Statement st;
	private ResultSet rs;
	
	 public UserDao() {
		 connection = DatabaseConnection.getConnection();
	 }
	 
	 public User validateUser(String username, String password) {
		 String query = "select * from user where user_name = '" + username + "'";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				user = new User();
				user.setUserid(rs.getString("user_id"));
				user.setName(rs.getString("user_name"));
				user.setSsn(rs.getString("ssn"));
				user.setPassword(rs.getString("password"));
				user.setDob(rs.getDate("dob"));
				user.setRole(rs.getString("role"));
				user.setAcc_bal(rs.getFloat("acc_bal"));
				user.setAddress(rs.getString("address"));
				user.setRating(rs.getInt("rating"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		if(user!= null && user.getPassword().equals(password)) {
			System.out.println("Valid User");
			return user;
		}
		System.out.println("Not a valid User = " + username + " " + password);
		 return null;
	 }
	 
	 public int regUser(User user) {
		String query = "insert into user values(?,?,?,?,?,?,?,?,?)";
		int res = -1;
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user.getUserid());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getSsn());
			pst.setString(4, user.getPassword());
			pst.setDate(5, user.getDob());
			pst.setString(6, user.getRole());
			pst.setFloat(7, user.getAcc_bal());
			pst.setString(8, user.getAddress());
			pst.setInt(9, user.getRating());
			res = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return res;
	 }	 
	 
	 public Department getDepartmentDetails(String userid) {
		 Department dept = null;
		 String query = "select * from department where u_id = '" + userid + "'";
		 try {
				st = connection.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					dept = new Department();
					dept.setDept_id(rs.getString("dept_id"));
					dept.setDept_name(rs.getString("dept_name"));
					dept.setStart_date(rs.getDate("start_date"));
					dept.setUser_id(rs.getString("u_id"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return dept;
	 }
	 
	 public User getUserbyName(String username) {
		 String query = "select * from user where user_name = '" + username + "'";
			try {
				st = connection.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					user = new User();
					user.setUserid(rs.getString("user_id"));
					user.setName(rs.getString("user_name"));
					user.setSsn(rs.getString("ssn"));
					user.setPassword(rs.getString("password"));
					user.setDob(rs.getDate("dob"));
					user.setRole(rs.getString("role"));
					user.setAcc_bal(rs.getFloat("acc_bal"));
					user.setAddress(rs.getString("address"));
					user.setRating(rs.getInt("rating"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
			if(user!= null) {
				return user;
			}
			 
			 return null;
	 }
	 
	 public User getUserbyID(String userid) {
		 String query = "select * from user where user_id = '" + userid + "'";
			try {
				st = connection.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					user = new User();
					user.setUserid(rs.getString("user_id"));
					user.setName(rs.getString("user_name"));
					user.setSsn(rs.getString("ssn"));
					user.setPassword(rs.getString("password"));
					user.setDob(rs.getDate("dob"));
					user.setRole(rs.getString("role"));
					user.setAcc_bal(rs.getFloat("acc_bal"));
					user.setAddress(rs.getString("address"));
					user.setRating(rs.getInt("rating"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
			if(user!= null) {
				return user;
			}
			 
			 return null;
	 }
	 
	 public int updateBalance(String userid, int price) {
		 int res = -1;
		 User bidder = getUserbyID(userid);
		 float newbal = bidder.getAcc_bal() - price;
		 if(newbal < 0) {
			 return -1;
		 } 
		 String query = "update user set acc_bal=" +newbal+ " where user_id = '" + userid + "'";
		try {
			Statement st = connection.createStatement();
			res = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return res;
	 }
	 
	 public int addCredits(String userid, int credits) {
		 int res = -1;
		 User bidder = getUserbyID(userid);
		 float newbal = bidder.getAcc_bal() + credits;
		 String query = "update user set acc_bal=" +newbal+ " where user_id = '" + userid + "'";
		try {
			Statement st = connection.createStatement();
			res = st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
			 return res;
	 }
	 
	 public List<User> getAllUsers() {
		 String query = "select * from user";
		 List<User> users = new ArrayList<User>();
			try {
				st = connection.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					user = new User();
					user.setUserid(rs.getString("user_id"));
					user.setName(rs.getString("user_name"));
					user.setSsn(rs.getString("ssn"));
					user.setPassword(rs.getString("password"));
					user.setDob(rs.getDate("dob"));
					user.setRole(rs.getString("role"));
					user.setAcc_bal(rs.getFloat("acc_bal"));
					user.setAddress(rs.getString("address"));
					user.setRating(rs.getInt("rating"));
					users.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return users;
	 }
	 
	 public List<Message> getMyMessages(String userid) {
		 String query = "select * from message where receiver_id='" + userid + "'";
		 List<Message> messages = new ArrayList<Message>();
			try {
				st = connection.createStatement();
				rs = st.executeQuery(query);
				while(rs.next()) {
					Message msg = new Message();
					msg.setReceiver_id(rs.getString("receiver_id"));
					msg.setMessage_id(rs.getInt("message_id"));
					msg.setSubject(rs.getString("subject"));
					msg.setMessage_msg(rs.getString("message"));
					msg.setSender_id(rs.getString("sender_id"));
					messages.add(msg);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return messages;
	 }
	 
	 public int sendMessage(Message message) {
		 	String query = "insert into message(sender_id, receiver_id, subject, message) values(?,?,?,?)";
			int res = -1;
			try {
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, message.getSender_id());
				pst.setString(2, message.getReceiver_id());
				pst.setString(3, message.getSubject());
				pst.setString(4, message.getMessage_msg());
				
				res = pst.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
			 return res;
	 }
	 
}

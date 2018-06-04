package Bidding.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
	private static Connection connection;
	private static String url = "jdbc:mysql://35.196.90.27:3306/ub_bidding";
//	private static String url = "jdbc:mysql://localhost:3306/ub_bidding";
	
	public static void ConnectToDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, "root", "12345");
//			connection = DriverManager.getConnection(url, "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (connection != null) {
            System.out.println("You made it, take control your database now!");
		} 
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			ConnectToDB();
		}
		return connection;
	}
	
	public List<List<Object>> processQuery(String query) {
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

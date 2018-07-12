package javas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {
	private int userID;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshop", "root", "");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean userlogin(String username, String password) {
		Connection conn = getConnection();
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
			ResultSet rs = stmt.executeQuery(sql);// STEP 5: Extract data from result set
			if (rs.next()) {// Retrieve by column name
				int id = rs.getInt("userID");
				String uname = rs.getString("username");
				String first = rs.getString("password");
				/*
				setUserID(id);
				System.out.print("ID: " + id);
				System.out.print(", Age: " + uname);
				System.out.print(", First: " + first);
				
				*/
				rs.close();
				stmt.close();
				return true;
			} else {
				rs.close();
				stmt.close();
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void setUserID(int id)
	{
		userID = id;
	}
	
	public int getUserID() {
		return userID;
	}
	

	public boolean userSignup(String username, String password) {
		try {
			Connection conn = getConnection();

			String sql = " INSERT INTO users (username, password)" + " values (?, ?)";

			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			
			preparedStmt.execute();

			conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public void basketadd(int userID, int bookID) {
		try {
			Connection conn = getConnection();

			String sql = " INSERT INTO basket (userID, bookID)" + " values (?, ?)";

			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, userID);
			preparedStmt.setInt(2, bookID);

			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void deletebasket(int userID , int bookID, int basketID)
	{
		try {
		Connection conn = getConnection();
		 String sql = "DELETE FROM basket where userID = ? AND bookID = ? AND basketID = ?";
	      PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setInt(1, userID );
	      preparedStmt.setInt(2, bookID);
	      preparedStmt.setInt(3, basketID);
	      preparedStmt.execute();
	      
	      conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearbasket(int userID)
	{
		try {
			Connection conn = getConnection();
			 String sql = "DELETE FROM basket where userID = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(sql);
		      preparedStmt.setInt(1, userID );
		      preparedStmt.execute();
		      
		      conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void deleteuser(int userID )
	{
		try {
		Connection conn = getConnection();
		 String sql = "DELETE FROM users where userID = ?";
	      PreparedStatement preparedStmt = conn.prepareStatement(sql);
	      preparedStmt.setInt(1, userID );
	      preparedStmt.execute();
	      
	      conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletebook(int bookID)
	{
		try {
			Connection conn = getConnection();
			 String sql = "DELETE FROM books where bookID = ?";
		      PreparedStatement preparedStmt = conn.prepareStatement(sql);
		      preparedStmt.setInt(1, bookID );
		      preparedStmt.execute();
		      
		      conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void addBook(String bookname , int price , String imageurl)
	{
		try {
			Connection conn = getConnection();

			String sql = " INSERT INTO books (detail, price,image)" + " values (?, ?,?)";

			PreparedStatement preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, bookname);
			preparedStmt.setInt(2, price);
			preparedStmt.setString(3, imageurl);

			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {

	}

}

package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.model.BlogEntry;

public class Db {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	
	public Db() {
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect(null);
			initDatabase();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void connect(String dbname) throws SQLException {
		
		if(dbname != null)
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname, "root", "");
		else
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
	}
	
	public int addUser(String username, String password) {
		
		try 
		{
			 String sql = "INSERT INTO users(user) VALUES(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			
			sql =  "SELECT userID FROM 	users WHERE user=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			int userID = -1;
			if(rs.next())
				userID = rs.getInt(1);

			sql = "INSERT INTO password(password,userID) VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, userID);
			System.out.println(pstmt.executeUpdate());
			return 0;
		}
		catch(Exception e) 
		{
			return -1;
		}
	}

	
	
	public void closeConnection() {
		try {
			this.con.close();
		} catch (SQLException e) {
			System.out.println("Connection konnten nicht geshlossen werden.");
			e.printStackTrace();
		}
	}
	
	private int initDatabase(){
		
		try 
		{
			final String sql = "CREATE DATABASE IF NOT EXISTS blog";
			this.pstmt = con.prepareStatement(sql);
			this.pstmt.executeUpdate();
			//close con
			closeConnection();
			//new con
			connect("blog");
			System.out.println("Kommt oder nicht");
			initTables();
			return 0;
		}
		catch(Exception e)
		{
			System.out.println("EXEPTION");
			return -1;
		}
	}
	
	private int initTables() {
		
		try 
		{
			final String[] sql ={
					
					"CREATE TABLE IF NOT EXISTS users 	 	(	userID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
																+ 	"user VARCHAR(60) NOT NULL )",
															
					"CREATE TABLE IF NOT EXISTS password		(	userPassID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
																+ " password VARCHAR(60) NOT NULL,"
																+ " userID INT, "
																+ "FOREIGN KEY(userID) REFERENCES users(userID) ON UPDATE CASCADE ON DELETE CASCADE )",
															
					"CREATE TABLE IF NOT EXISTS blogeintraege	(	blogID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, "
																+  	"blogeintrag TEXT )",
															
					"CREATE TABLE  IF NOT EXISTS blogDate		(	blogDateID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,"
																+ "date DATE,"
																+ "userID INT,"
																+ "blogID INT,"
																+ " FOREIGN KEY(userID) REFERENCES users(userID) ON UPDATE CASCADE ON DELETE CASCADE,"
																+ " FOREIGN KEY(blogID) REFERENCES blogeintraege(blogID) ON UPDATE CASCADE ON DELETE CASCADE)",
			};
			
			for(int i = 0; i < sql.length; i++ ) 
			{
				pstmt = con.prepareStatement(sql[i]);
				pstmt.executeUpdate();
			}
		
			return 0;
		}
		catch(Exception e) 
		{
			return -1;
		}
		
	}
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
		
}//class

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	// DBConnection Class 생성자 
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:(각자포트입력하세여)/(각자 데이터베이스 이름 입력하세여)?serverTimezone=UTC", "root", "각자 패스워드 입력하세여");
			st = con.createStatement();
		} catch(Exception e) {
			System.out.println("Database Connection Error : " + e.getMessage());
		}
	}
}

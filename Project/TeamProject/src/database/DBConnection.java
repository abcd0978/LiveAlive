package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	// DBConnection Class ������ 
	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:(������Ʈ�Է��ϼ���)/(���� �����ͺ��̽� �̸� �Է��ϼ���)?serverTimezone=UTC", "root", "���� �н����� �Է��ϼ���");
			st = con.createStatement();
		} catch(Exception e) {
			System.out.println("Database Connection Error : " + e.getMessage());
		}
	}
}

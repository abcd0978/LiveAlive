package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	protected static Connection con = null;
	protected static Statement st = null;
	protected ResultSet rs;
	
	// DBConnection Class »ý¼ºÀÚ 
	public DBConnection()
	{
		try
		{
			if(con == null) 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://14.38.252.76/test1?serverTimezone=UTC", "kohd", "0001");
			}
			if(st == null) 
			{
				st = con.createStatement();
			}
			System.out.println("database cooooooooooooooonnected");
		} catch(Exception e) 
		{
			System.out.println("Database Connection Error : " + e.getMessage());
		}
	}
	
	
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//싱글톤 패턴
public class DBConnection {
	protected static Connection con = null;
	protected static Statement st = null;
	protected static ResultSet rs;
	private static DBConnection db;

	private DBConnection()//생성자는 접근못하게 막아놓음
	{}
	public static DBConnection getInstance()
	{
		if(db==null)//전역객체가 null이면 생성해서 반환
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
				db = new DBConnection();
				System.out.println("데이터베이스 객체 생성됨");
			} catch(Exception e) 
			{
				System.out.println("Database Connection Error : " + e.getMessage());
			}
		}
		return db;
	}
}

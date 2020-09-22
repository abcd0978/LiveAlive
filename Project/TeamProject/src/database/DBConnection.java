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
	private static String __name;//����� �̸� �����
	private static int pk;
	// DBConnection Class ������ 
	public DBConnection()
	{
		try
		{
			if(con == null) 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?serverTimezone=UTC", "root", "0978");
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
	public void register(String id, String pass, String name) throws SQLException
	{
		String query = "INSERT INTO login(loginid, password, name) VALUES('"+id+"','"+pass+"','"+name+"');";
		System.out.println(query);
		if(st.executeUpdate(query)>0) 
		{
			System.out.println("inserted.");
		}
	}
	
	public boolean login(String id, String pass) throws SQLException{
		String query = "SELECT * FROM login "
				+ "WHERE loginid = '"+id+"' AND password ='"+pass+"';";
		System.out.println(query);
		if(st.execute(query)) {
			rs = st.getResultSet();
		}
		while(rs.next()) {
			String __id = rs.getString("loginid");
			String __pw = rs.getString("password");
			if(__id.equals(id) && __pw.equals(pass)) {
				__name = rs.getString("name");
				pk = rs.getInt("id");
				return true;
			}
		}
		return false;
	}
	
	public String getName() {
		return __name;
	}
	public int getid()
	{
		return pk;
	}
}

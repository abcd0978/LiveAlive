package database;

import java.sql.SQLException;

public class member{

	private static String __name;//사용자 이름 저장됨
	private static int pk;
	private DBConnection db;
	public member()
	{
		db = DBConnection.getInstance();
	}
	public void register(String id, String pass, String name) throws SQLException
	{
		String query = "INSERT INTO member(loginid, password, name) VALUES('"+id+"','"+pass+"','"+name+"');";
		System.out.println(query);
		if(DBConnection.st.executeUpdate(query)>0) 
		{
			System.out.println("inserted.");
		}
	}
	public boolean login(String id, String pass) throws SQLException{
		String query = "SELECT * FROM member "
				+ "WHERE loginid = '"+id+"' AND password ='"+pass+"';";
		System.out.println(query);
		if(DBConnection.st.execute(query)) {
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		while(DBConnection.rs.next()) {
			String __id = DBConnection.rs.getString("loginid");
			String __pw = DBConnection.rs.getString("password");
			if(__id.equals(id) && __pw.equals(pass)) {
				__name = DBConnection.rs.getString("name");
				pk = DBConnection.rs.getInt("id");
				return true;
			}
		}
		return false;
	}
	
	public boolean isId_dup(String id) throws SQLException {
		String query = "SELECT * FROM member "
				+ "WHERE loginid = '"+id+"';";
		System.out.println(query);
		if(DBConnection.st.execute(query)) {
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		
		while(DBConnection.rs.next()) {
			String _id = DBConnection.rs.getString("loginid");
			//id == query true일 경우 겹치는 상황
			if(_id.equals(id)) {
				System.out.println("ID is dup");
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

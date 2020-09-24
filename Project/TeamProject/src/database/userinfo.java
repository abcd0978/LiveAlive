package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class userinfo extends member
{
	private DBConnection db;
	private member mb;
	private Calendar cal;
	int user_id;
	
	public userinfo()
	{
		mb = new member();
		System.out.println(mb.getName()+"의 정보 " + "키번호 : "+mb.getid());
		user_id = mb.getid();
		
	}
	/*
	 * public boolean is_empty(String parm) throws SQLException//비었는지 확힌하는 메소드 {
	 * String query = "SELECT * FROM login " +"WHERE id = '"+user_id+"';"; String
	 * there = ""; if(st.execute(query)) { System.out.println("query executed"); rs
	 * = st.getResultSet(); } while(rs.next()) { there = rs.getString(parm); }
	 * return there==null ? true : false;//조회해서 null값이면 true를 리턴한다. }
	 */
	
	public String getTall() throws SQLException//키를 얻는 메소드
	{
		float result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			System.out.println("getTall query executed");
			rs = st.getResultSet();
		}
		while(rs.next())
		{
		result = rs.getFloat("tall");
		}
		if(result == 0.0)
		{
			return "no data";
		}
		return Float.toString(result);
	}
	
	public void setTall(float tall) throws SQLException
	{
		String query = "UPDATE member SET"+" tall ='"+tall
				+"' WHERE id ='"+user_id+"';";
		System.out.println(query);
		st.execute(query);
	}
	
	public String getAge() throws SQLException
	{
		int result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			System.out.println("getAge query executed");
			rs = st.getResultSet();
		}
		while(rs.next())
		{
			result = rs.getInt("age");
		}
		if(result == 0)
		{
			return "no data";
		}
		return Integer.toString(Calendar.YEAR);
	}
	
		public void setAge(int age) throws SQLException
	{
		String query = "UPDATE member SET"+" age ='"+age
				+"' WHERE id ='"+user_id+"';";
		System.out.println(query);
		st.execute(query);
	}
	
	public String getSex() throws SQLException
	{
		int result = 1;//1이 여성 
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			System.out.println("getSex query executed");
			rs = st.getResultSet();
		}
		while(rs.next())
		{
			result = rs.getInt("sex");
		}
		System.out.println(result);
		return result==1 ? "여성" : "남성" ;
	}

	public void setSex(int sex) throws SQLException//0은 남성, 1은 여성이다
	{
		String query = "UPDATE member SET"+" sex ='"+sex
				+"' WHERE id ='"+user_id+"';";
		System.out.println(query);
		st.execute(query);
	}
	
}

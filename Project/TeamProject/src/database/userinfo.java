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
		System.out.println(mb.getName()+"�� ���� " + "Ű��ȣ : "+mb.getid());
		user_id = mb.getid();
		
	}
	/*
	 * public boolean is_empty(String parm) throws SQLException//������� Ȯ���ϴ� �޼ҵ� {
	 * String query = "SELECT * FROM login " +"WHERE id = '"+user_id+"';"; String
	 * there = ""; if(st.execute(query)) { System.out.println("query executed"); rs
	 * = st.getResultSet(); } while(rs.next()) { there = rs.getString(parm); }
	 * return there==null ? true : false;//��ȸ�ؼ� null���̸� true�� �����Ѵ�. }
	 */
	
	public String getTall() throws SQLException//Ű�� ��� �޼ҵ�
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
		int result = 1;//1�� ���� 
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
		return result==1 ? "����" : "����" ;
	}

	public void setSex(int sex) throws SQLException//0�� ����, 1�� �����̴�
	{
		String query = "UPDATE member SET"+" sex ='"+sex
				+"' WHERE id ='"+user_id+"';";
		System.out.println(query);
		st.execute(query);
	}
	
}

package database;
import java.sql.SQLException;
import java.util.Calendar;

public class userinfo extends member
{
	private member mb;
	private Calendar cal;
	private int year,month,date;
	private int user_id;
	String monthS;
	public userinfo()
	{
		cal = Calendar.getInstance();
		mb = new member();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH)+1;
		this.date = cal.get(Calendar.DATE);
		System.out.println(mb.getName()+"의 정보 " + "키번호 : "+mb.getid());
		user_id = mb.getid();
		if(this.month > 9)//db에서 는 1~9월에 0이 붙어서 출력되므로 1~9월에는 앞에 0을 붙인 monthS를 사용한다. 
			monthS = "1"+Integer.toString((this.month%10));
		else
			 monthS="0"+Integer.toString(this.month); 
	}
	public String getTall() throws SQLException//키를 얻는 메소드
	{
		float result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			rs = st.getResultSet();
		}
		while(rs.next())
		{
		result = rs.getFloat("tall");
		}
		if(result == 0.0)
		{
			return "no";
		}
		return Float.toString(result);
	}
	public void setTall(double tall) throws SQLException//키를 바꾸는 메소드
	{
		String query = "UPDATE member SET"+" tall ='"+tall
				+"' WHERE id ='"+user_id+"';";
		st.execute(query);
	}
	public String getAge() throws SQLException
	{
		int result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			rs = st.getResultSet();
		}
		while(rs.next())
		{
			result = rs.getInt("age");
		}
		if(result == 0)
		{
			return "no";
		}
		return Integer.toString(cal.get(Calendar.YEAR)-result);//현재년에서 태어난연도를 뺀다
	}
	public String getSex() throws SQLException
	{
		int result = 1;//1이 여성 
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(st.execute(query))
		{
			rs = st.getResultSet();
		}
		while(rs.next())
		{
			result = rs.getInt("sex");
		}
		return result==1 ? "여성" : "남성" ;
	}
	public void setAge(int age) throws SQLException//태어난 년도를 입력
	{
		String query = "UPDATE member SET"+" age ='"+age
				+"' WHERE id ='"+user_id+"';";
		st.execute(query);
	}
	public void setSex(int sex) throws SQLException//0은 남성, 1은 여성이다
	{
		String query = "UPDATE member SET"+" sex ='"+sex
				+"' WHERE id ='"+user_id+"';";
		st.execute(query);
	}
	public String getResentWeight() throws SQLException//최근에 입력한 체중을 리턴한다 
	{
		String weight="";
		String query = "SELECT * FROM user_info2 "
				+"WHERE id = '"+user_id
				+"' ORDER BY DATE DESC;";
		if(st.execute(query))
		{
			rs = st.getResultSet();
		}
		while(rs.next())
		{
			if(!rs.getString("weight").equals("0.0"))
			{
				weight = rs.getString("weight");
				break;
			}
		}
		return weight;
	}
	public void initUserInfo2() throws SQLException//시작할때 오늘날짜의 user_info행이 생성되었는지 안되었는지 확인하고 안되었으면 생성함.
	{
		String date="";
		String query = "SELECT * FROM user_info2 "//현재 접속한 유저의 user_info를 날짜순으로 나열하는 쿼리문
				+"WHERE id = '"+user_id
				+"' ORDER BY DATE DESC;";
		if(st.execute(query))
		{
			rs = st.getResultSet();
		}
		rs.next();
		date = rs.getString("date");
		if(!date.equals(year+"-"+monthS+"-"+(this.date)))//만약 가장 최근에 생성한 user_info데이터가 현재가 아니라면,날짜만있고 전부 NULL로 데이터를 생성한다
		{
			String query2 = "INSERT INTO user_info2 (DATE,id) "
					+ "VALUE ('"+year+"-"+monthS+"-"+this.date+"','"+user_id+"');";
			System.out.println(query2);
			if(st.execute(query2))
			{
				System.out.println("new day data inserted");
			}
		}
		else
		{
			System.out.println("alreay exist");//이미 존재할시에.
		}
	}
	public void setCurrentdayWeight(double weight) throws SQLException //오늘의 체중을 입력해준다
	{
		String query = "UPDATE user_info2"
				+" SET weight = '"+weight
				+"' WHERE DATE = '"+year+"-"+monthS+"-"+this.date+"' AND id ='"+user_id+"';";
		System.out.println(query);
		st.execute(query);
		//System.out.println("current weight updated");
	}
}

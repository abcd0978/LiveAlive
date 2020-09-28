package database;
import java.sql.SQLException;
import java.util.Calendar;

public class userinfo extends member
{
	private member mb;
	private Calendar cal;
	private int year,month,date;
	private int user_id;
	public userinfo()
	{
		cal = Calendar.getInstance();
		mb = new member();
		this.year = cal.get(Calendar.YEAR);//������ �⵵
		this.month = cal.get(Calendar.MONTH)+1;//�����Ǵ�
		this.date = cal.get(Calendar.DATE);//������ ��¥
		System.out.println(mb.getName()+"�� ���� " + "Ű��ȣ : "+mb.getid());
		user_id = mb.getid();
	}
	public String getMonth(int month)
	{
		String monthS;
		if(this.month > 9)//db���� �� 1~9���� 0�� �پ ��µǹǷ� 1~9������ �տ� 0�� ���� monthS�� ����Ѵ�. 
			monthS = "1"+Integer.toString((this.month%10));
		else
			 monthS="0"+Integer.toString(this.month); 
		return monthS;
	}
	public String getTall() throws SQLException//Ű�� ��� �޼ҵ�
	{
		float result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(DBConnection.st.execute(query))
		{
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		while(DBConnection.rs.next())
		{
		result = DBConnection.rs.getFloat("tall");
		}
		if(result == 0.0)
		{
			return "0";
		}
		return Float.toString(result);
	}
	public void setTall(double tall) throws SQLException//Ű�� �ٲٴ� �޼ҵ�
	{
		String query = "UPDATE member SET"+" tall ='"+tall
				+"' WHERE id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public String getAge() throws SQLException
	{
		int result = 0;
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(DBConnection.st.execute(query))
		{
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		while(DBConnection.rs.next())
		{
			result = DBConnection.rs.getInt("age");
		}
		if(result == 0)
		{
			return "0";
		}
		return Integer.toString(cal.get(Calendar.YEAR)-result);//����⿡�� �¾������ ����
	}
	public String getSex() throws SQLException
	{
		int result = 1;//1�� ���� 
		String query = "SELECT * FROM member "
				+"WHERE id = '"+user_id+"';";
		if(DBConnection.st.execute(query))
		{
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		while(DBConnection.rs.next())
		{
			result = DBConnection.rs.getInt("sex");
		}
		return result==1 ? "����" : "����" ;
	}
	public void setAge(int age) throws SQLException//�¾ �⵵�� �Է�
	{
		String query = "UPDATE member SET"+" age ='"+age
				+"' WHERE id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public void setSex(int sex) throws SQLException//0�� ����, 1�� �����̴�
	{
		String query = "UPDATE member SET"+" sex ='"+sex
				+"' WHERE id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public String getResentWeight() throws SQLException//�ֱٿ� �Է��� ü���� �����Ѵ� 
	{
		String weight="0";
		String query = "SELECT * FROM user_info2 "
				+"WHERE id = '"+user_id
				+"' ORDER BY DATE DESC;";
		if(DBConnection.st.execute(query))
		{
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		while(DBConnection.rs.next())//�ֱٵ����� 
		{
			if(!DBConnection.rs.getString("weight").equals("0.0"))//weight�� 0�̾ƴϸ鼭 �ֱ��ΰ�
			{
				weight = DBConnection.rs.getString("weight");
				break;
			}
		}
		return weight;
	}
	public void initUserInfo2() throws SQLException//�����Ҷ� ���ó�¥�� user_info���� �����Ǿ����� �ȵǾ����� Ȯ���ϰ� �ȵǾ����� ������.
	{
		String date="";
		String query = "SELECT * FROM user_info2 "//���� ������ ������ user_info�� ��¥������ �����ϴ� ������
				+"WHERE id = '"+user_id
				+"' ORDER BY DATE DESC;";
		if(DBConnection.st.execute(query))//��������
		{
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		if(DBConnection.rs.next())//���������Ͱ� �����ϴ°�
		{
			System.out.println(DBConnection.rs);
			date = DBConnection.rs.getString("date");//�����ֱ��� �����͸� �޾ƿ´�
			if(!date.equals(year+"-"+getMonth(month)+"-"+(this.date)))//���� ���� �ֱٿ� ������ user_info�����Ͱ� ���簡 �ƴ϶��,��¥���ְ� ���� NULL�� �����͸� �����Ѵ�
				{
				String query2 = "INSERT INTO user_info2 (DATE,id) "
						+ "VALUE ('"+year+"-"+getMonth(month)+"-"+this.date+"','"+user_id+"');";
				System.out.println(query2);
				DBConnection.st.execute(query2);
				System.out.println("hello ");
			}
			else{
				System.out.println("alreay exist");//�̹� �����ҽÿ�.
			}
		}
		else//���������Ͱ� �������������� �ű�����
		{
			String query2 = "INSERT INTO user_info2 (DATE,id) "
					+ "VALUE ('"+year+"-"+getMonth(month)+"-"+this.date+"','"+user_id+"');";
			DBConnection.st.execute(query2);
			System.out.println("welcome new user");
		}
		
	}
	public void setCurrentdayWeight(double weight) throws SQLException //������ ü���� �Է����ش�
	{
		String query = "UPDATE user_info2"
				+" SET weight = '"+weight
				+"' WHERE DATE = '"+year+"-"+getMonth(month)+"-"+this.date+"' AND id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public void updateCurrentIntake(double intake) throws SQLException //������ ���� ������ �������� ��ϵɶ����� ���Ѵ�.
	{
		String query = "UPDATE user_info2"
				+" SET intake = intake +"+intake
				+" WHERE DATE = '"+year+"-"+getMonth(month)+"-"+this.date+"' AND id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public void updateCurrentMoves(int moves) throws SQLException
	{
		String query = "UPDATE user_info2"
				+" SET moves = moves +"+moves
				+" WHERE DATE = '"+year+"-"+getMonth(month)+"-"+this.date+"' AND id ='"+user_id+"';";
		DBConnection.st.execute(query);
	}
	public String getIntake(String year,String month,String date) throws SQLException
	{
		String query = "SELECT intake FROM user_info2 WHERE"
				+" date = '"+year+"-"+month+"-"+date+"' AND id = '"+user_id+"';";//user_info���̺��� �ش��ϴ� �������� ������ �����´�
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		if(DBConnection.rs.next())
			return DBConnection.rs.getString("intake");
		else
			return "0";
	}
	public String getMove(String year,String month,String date) throws SQLException
	{
		String query = "SELECT moves FROM user_info2 WHERE "
				+"date = '"+year+"-"+month+"-"+date+"' AND id = '"+user_id+"';";//user_info���̺��� �ش��ϴ� �������� ������ �����´�
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		if(DBConnection.rs.next())
			return DBConnection.rs.getString("moves");
		else
			return "0";
	}
}

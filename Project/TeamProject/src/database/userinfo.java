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
		System.out.println(mb.getName()+"�� ���� " + "Ű��ȣ : "+mb.getid());
		user_id = mb.getid();
		if(this.month > 9)//db���� �� 1~9���� 0�� �پ ��µǹǷ� 1~9������ �տ� 0�� ���� monthS�� ����Ѵ�. 
			monthS = "1"+Integer.toString((this.month%10));
		else
			 monthS="0"+Integer.toString(this.month); 
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
			if(!date.equals(year+"-"+monthS+"-"+(this.date)))//���� ���� �ֱٿ� ������ user_info�����Ͱ� ���簡 �ƴ϶��,��¥���ְ� ���� NULL�� �����͸� �����Ѵ�
				{
				String query2 = "INSERT INTO user_info2 (DATE,id) "
						+ "VALUE ('"+year+"-"+monthS+"-"+this.date+"','"+user_id+"');";
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
					+ "VALUE ('"+year+"-"+monthS+"-"+this.date+"','"+user_id+"');";
			DBConnection.st.execute(query2);
			System.out.println("welcome new user");
		}
		
	}
	public void setCurrentdayWeight(double weight) throws SQLException //������ ü���� �Է����ش�
	{
		String query = "UPDATE user_info2"
				+" SET weight = '"+weight
				+"' WHERE DATE = '"+year+"-"+monthS+"-"+this.date+"' AND id ='"+user_id+"';";
		System.out.println(query);
		DBConnection.st.execute(query);
	}
}

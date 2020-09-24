package database;

import java.sql.SQLException;

import lib.*;

public class user_days extends member
{
	private member mb;//member��ü
	private int user_id;//������ �������̵�
	private selctionSort ss;
	public user_days()
	{
		mb = new member();
		user_id = mb.getid();
		ss = new selctionSort();
	}
	public String monthToString(int month)
	{//db���� 1~9���� 0�� �پ �����Ƿ� 1~9�������� 0�� ���� monthS�� ���϶���.
		String monthS="";
		if(month > 9)//db���� �� 1~9���� 0�� �پ ��µǹǷ� 1~9������ �տ� 0�� ���� monthS�� ����Ѵ�. 
			monthS = "1"+Integer.toString((month%10));
		else
			 monthS="0"+Integer.toString(month);
		return monthS;
	}
	public void saveTodo(String todo,int importance,int year,int month,int date) throws SQLException//������ �����Ѵ�.
	{
		int count = 0;//�� ������� ���� ������ ��ִ��� Ȯ���ϴ� �޼ҵ�
		String query = "SELECT * FROM user_days WHERE DATE = '"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"';";
		System.out.println(query);
		st.execute(query);
		rs = st.getResultSet();
		while(rs.next())
		{
			count++;
		}
		if(count<6)//�� 6���� �����Ѵ�.
		{
			String query2 = "INSERT INTO user_days (todo,DATE,impt,member_id)"
					+" VALUE ('"+todo+"','"+year+"-"+this.monthToString(month)+"-"+date+"','"+importance+"','"+user_id+"');";
			System.out.println(query2);
			st.execute(query2);//������ �����Ѵ�. ������ �����.
		}
		else
		{
			System.out.println("you're too busy bro");//6���� �����ÿ�
		}
	}
	public String[] getTodo_order_by_alphabet(int year,int month,int date) throws SQLException//������ �����ټ����� ���� 
	{
		String[] result = new String[6];
		int i=0;
		String query = "SELECT * FROM user_days"
				+" WHERE DATE ='"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' ;";
		System.out.println(query);
		st.execute(query);
		rs = st.getResultSet();
		while(rs.next())//resultSet next�� null�� �ƴҶ�����
		{
			result[i] = rs.getString("todo");//todo�� ��Ʈ�� ��ü�� �����Ѵ�
			i++;//�ε����� �÷��ش�.
		}
		for(int j=0;j<6;j++)//result��Ʈ�� ��ü�� ó������ ������
		{
			if(result[j]==null)//���� null�� ��Ұ��ִٸ�
				result[j] = "";//��Ʈ��
		}
		result = ss.sort_String(result);//�׵ڿ� ��Ʈ���迭�� �����Ѵ�.
		return result;//������ ��Ʈ����ü�� �����Ѵ�.
	}
	public String[] getTodo_order_by_importance(int year,int month,int date) throws SQLException//������ �߿䵵������ ���� �߿䵵�� �����ÿ� ���ĺ�������
	{
		String[] result = new String[6];//�ִ�6�� �̹Ƿ�
		int i=0;
		String query = "SELECT * FROM user_days WHERE DATE = '"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' order by impt desc;";
		st.execute(query);//������ ����
		rs = st.getResultSet();//resultSet���
		while(rs.next())
		{
			result[i] = rs.getString("todo");
			i++;
		}
		for(int j=0;j<6;j++)
		{
			if(result[j]==null)//��Ұ� null�̸�
				result[j]="";//��Ʈ������
		}
		return result;
	}
	public void removeTodo(int year,int month,int date)//������ �����Ѵ�
	{
		
	}
	public boolean is_todoDay(int year,int month,int date) throws SQLException//�޷¿��� ������ �ִ� ������ �ƴ��� �����ϴ� �޼ҵ�
	{
		int count = 0;
		String query = "SELECT * FROM user_days"
				+" WHERE DATE ='"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' ;";
		st.execute(query);
		rs = st.getResultSet();
		while(rs.next())
			count++;
		return count==0? false : true;
	}
}

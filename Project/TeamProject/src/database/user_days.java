package database;

import java.sql.SQLException;

import lib.*;

public class user_days
{
	private member mb;//member객체
	private int user_id;//유저의 고유아이디
	private selctionSort ss;
	public user_days()
	{
		mb = new member();
		user_id = mb.getid();
		ss = new selctionSort();
	}
	public String monthToString(int month)
	{//db에는 1~9월이 0이 붙어서 나오므로 1~9뭘까지는 0을 붙힌 monthS을 리턴란다.
		String monthS="";
		if(month > 9)//db에서 는 1~9월에 0이 붙어서 출력되므로 1~9월에는 앞에 0을 붙인 monthS를 사용한다. 
			monthS = "1"+Integer.toString((month%10));
		else
			 monthS="0"+Integer.toString(month);
		return monthS;
	}
	public void saveTodo(String todo,int importance,int year,int month,int date) throws SQLException//일정을 저장한다.
	{
		int count = 0;//현 사용자의 오늘 일정이 몇개있는지 확인하는 메소드
		String query = "SELECT * FROM user_days WHERE DATE = '"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"';";
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		while(DBConnection.rs.next())
		{
			count++;
		}
		if(count<6)//딱 6개만 저장한다.
		{
			String query2 = "INSERT INTO user_days (todo,DATE,impt,member_id)"
					+" VALUE ('"+todo+"','"+year+"-"+this.monthToString(month)+"-"+date+"','"+importance+"','"+user_id+"');";
			System.out.println(query2);
			DBConnection.st.execute(query2);//쿼리를 실행한다. 일정이 저장됨.
		}
		else
		{
			System.out.println("you're too busy bro");//6개가 넘을시에
		}
	}
	public String[] getTodo_order_by_alphabet(int year,int month,int date) throws SQLException//일정을 가나다순으로 정렬 
	{
		String[] result = new String[6];
		int i=0;
		String query = "SELECT * FROM user_days"
				+" WHERE DATE ='"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' ;";
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		while(DBConnection.rs.next())//resultSet next가 null이 아닐때까지
		{
			result[i] = DBConnection.rs.getString("todo");//todo를 스트링 객체에 저장한다
			i++;//인덱스를 늘려준다.
		}
		for(int j=0;j<6;j++)//result스트링 객체의 처음부터 끝까지
		{
			if(result[j]==null)//만약 null인 요소가있다면
				result[j] = "";//빈스트링
		}
		result = ss.sort_String(result);//그뒤에 스트링배열을 정렬한다.
		return result;//정렬한 스트링객체를 리턴한다.
	}
	public String[] getTodo_order_by_importance(int year,int month,int date) throws SQLException//일정을 중요도순으로 정렬 중요도가 같을시에 알파벳순으로
	{
		String[] result = new String[6];//최대6개 이므로
		int i=0;
		String query = "SELECT * FROM user_days WHERE DATE = '"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' order by impt desc;";
		DBConnection.st.execute(query);//쿼리문 실행
		DBConnection.rs = DBConnection.st.getResultSet();//resultSet얻기
		while(DBConnection.rs.next())
		{
			result[i] = DBConnection.rs.getString("todo");
			i++;
		}
		for(int j=0;j<6;j++)
		{
			if(result[j]==null)//요소가 null이면
				result[j]="";//빈스트링으로
		}
		return result;
	}
	public void removeTodo(String todo,int year,int month,int date) throws SQLException//일정을 삭제한다
	{
		String query ="DELETE FROM user_days "
				+ " WHERE date = '"+year+"-"+this.monthToString(month)+"-"+date+"' AND todo= '"+todo+"' AND member_id = '"+this.user_id+"';";
		String query2 = "ALTER TABLE user_days AUTO_INCREMENT=1;";
		String query3 = "SET @COUNT = 0;";
		String query4 = "UPDATE user_days SET id = @COUNT:=@COUNT+1;";
		DBConnection.st.execute(query);
		DBConnection.st.execute(query2);
		DBConnection.st.execute(query3);
		DBConnection.st.execute(query4);
	}
	public boolean is_todoDay(int year,int month,int date) throws SQLException//달력에서 일정이 있는 날인지 아닌지 구분하는 메소드
	{
		int count = 0;
		String query = "SELECT * FROM user_days"
				+" WHERE DATE ='"+year+"-"+this.monthToString(month)+"-"+date+"' AND member_id = '"+user_id+"' ;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		while(DBConnection.rs.next())
			count++;
		return count==0? false : true;
	}
}

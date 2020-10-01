package database;

import java.sql.SQLException;

public class health_info {
	private DBConnection db;
	
	public int numberofhealthInfoData() throws SQLException {
		int count;
		String query = "SELECT COUNT(title) AS count FROM health_info;";//음식이 몇개 저장되어있는지 세는 쿼리문.
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();//정보를 받아옴
		DBConnection.rs.next();
		count = DBConnection.rs.getInt("count");//받아온 정보를 count에저장함
		System.out.println("불러온 데이터 수  : " + count + " 개");
		return count;//count를 리턴함
	}
	
	public String getTitle(int dataNum) throws SQLException {
		String title = null;
		String query = "SELECT title FROM health_info" + " WHERE number = '"+dataNum+"';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		
		while(DBConnection.rs.next()) {
			title = DBConnection.rs.getString("title");
		}
		return title;
	}
	
	public String getText(int dataNum) throws SQLException {
		String text = null;
		String query = "SELECT text FROM health_info" + " WHERE number = '"+dataNum+"';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		
		while(DBConnection.rs.next()) {
			text = DBConnection.rs.getString("text");
		}
		return text;
	}
}

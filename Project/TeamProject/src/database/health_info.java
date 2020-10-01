package database;

import java.sql.SQLException;

public class health_info {
	private DBConnection db;
	
	public int numberofhealthInfoData() throws SQLException {
		int count;
		String query = "SELECT COUNT(title) AS count FROM health_info;";//������ � ����Ǿ��ִ��� ���� ������.
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();//������ �޾ƿ�
		DBConnection.rs.next();
		count = DBConnection.rs.getInt("count");//�޾ƿ� ������ count��������
		System.out.println("�ҷ��� ������ ��  : " + count + " ��");
		return count;//count�� ������
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

package database;

import java.sql.SQLException;
import lib.selctionSort;
public class foods 
{
	private food[] fd;//���� ��ü�迭
	private DBConnection db;
	private selctionSort ss;
	public foods()//db���� ���������� �޾Ƽ� ��ü�迭�� �޾ƿ´�.
	{
		db = DBConnection.getInstance();
		ss = new selctionSort();
	}
	public int numberOfFoods() throws SQLException//db�� ����� ������ ���� �����Ѵ�
	{
		int count;
		String query = "SELECT COUNT(name) AS count FROM foods; ";//������ � ����Ǿ��ִ��� ���� ������.
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();//������ �޾ƿ�
		DBConnection.rs.next();
		count = DBConnection.rs.getInt("count");//�޾ƿ� ������ count��������
		return count;//count�� ������
	}
	public food[] setFoodInfos() throws SQLException//��ü�迭�� ��� ���������� �޾ƿ�
	{
		int i = numberOfFoods();
		fd = new food[i];
		System.out.println(i);
		String query = "SELECT * FROM foods;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		i=0;
		while(DBConnection.rs.next())
		{ 
			fd[i] = new food();
			fd[i].setName(DBConnection.rs.getString("name"));
			fd[i].setSerM(Integer.parseInt(DBConnection.rs.getString("serve_amount")));
			fd[i].setUnit(DBConnection.rs.getString("unit"));
			fd[i].setKcal(Double.parseDouble(DBConnection.rs.getString("kcal")));
			fd[i].setProtein(Double.parseDouble(DBConnection.rs.getString("protein")));
			fd[i].setFat(Double.parseDouble(DBConnection.rs.getString("fat")));
			fd[i].setCarb(Double.parseDouble(DBConnection.rs.getString("carb")));
			i++;
		}
		return fd;
	}
	public food[] SLSortBy(food[] arr,int bywhat) throws SQLException//1�� ������,2�� ź��ȭ��,3�� �ܹ���,4�� ����
	{
		return ss.sort_food(arr, bywhat);
	}
}

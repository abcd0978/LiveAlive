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
		food[] temp = new food[i];//db�� ����� ������ ����ŭ �����Ѵ�.
		String query = "SELECT * FROM foods;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		System.out.println(i);
		i=0;
		while(DBConnection.rs.next())
		{
			temp[i] = new food();
			temp[i].setName(DBConnection.rs.getString("name"));
			temp[i].setSerM(Integer.parseInt(DBConnection.rs.getString("serve_amount")));
			temp[i].setUnit( DBConnection.rs.getString("unit"));
			temp[i].setKcal(Double.parseDouble(DBConnection.rs.getString("kcal")));
			temp[i].setProtein(Double.parseDouble(DBConnection.rs.getString("protein")));
			temp[i].setFat(Double.parseDouble(DBConnection.rs.getString("fat")));
			temp[i].setCarb(Double.parseDouble(DBConnection.rs.getString("carb")));
			i++;
		}
		return temp;
	}
	public food[] SLSortBy(food[] arr,int bywhat) throws SQLException//1�� ������,2�� ź��ȭ��,3�� �ܹ���,4�� ����
	{
		return ss.sort_food(arr, bywhat);
	}
}

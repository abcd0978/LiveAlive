package database;

import java.sql.SQLException;
import lib.selctionSort;
public class foods 
{
	private food[] fd;//음식 객체배열
	private DBConnection db;
	private selctionSort ss;
	public foods()//db에서 음식정보를 받아서 객체배열에 받아온다.
	{
		db = DBConnection.getInstance();
		ss = new selctionSort();
	}
	public int numberOfFoods() throws SQLException//db에 저장된 음식의 수를 리턴한다
	{
		int count;
		String query = "SELECT COUNT(name) AS count FROM foods; ";//음식이 몇개 저장되어있는지 세는 쿼리문.
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();//정보를 받아옴
		DBConnection.rs.next();
		count = DBConnection.rs.getInt("count");//받아온 정보를 count에저장함
		return count;//count를 리턴함
	}
	public food[] setFoodInfos() throws SQLException//객체배열에 모든 음식정보를 받아옴
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
	public food[] SLSortBy(food[] arr,int bywhat) throws SQLException//1은 열량순,2는 탄수화물,3은 단백질,4는 지방
	{
		return ss.sort_food(arr, bywhat);
	}
}

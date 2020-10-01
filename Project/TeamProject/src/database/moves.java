package database;

import java.sql.SQLException;

import lib.selctionSort;

public class moves 
{
	private move[] mv;
	private int max = 10;
	private DBConnection db;
	private userinfo ui;
	private selctionSort ss;
	int top;
	public moves()
	{
		top =-1;//탑 -1로 초기화
		db = DBConnection.getInstance();
		ui = new userinfo();
		ss = new selctionSort();
	}
	public move pop()
	{
		move temp = mv[top];
		mv[top] = null;
		top--;
		return temp;
	}
	public move[] get()
	{
		return this.mv;
	}
	public boolean isFull()
	{
		return  top == max-1 ? true : false;
	}
	public boolean isEmpty()
	{
		return top == -1 ? true : false;
	}
	public void push(String name,int kcal)
	{
		top++;
		this.mv[top] = new move();
		this.mv[top].setkcal(kcal);
		this.mv[top].setName(name);
	}
	public move[] setMoveInfo() throws SQLException
	{
		mv = new move[max];
		String query = "SELECT * FROM move;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		while(DBConnection.rs.next())
		{
			if(!isFull())
				System.out.println(DBConnection.rs.getString("name")+DBConnection.rs.getInt("kcal"));
				push(DBConnection.rs.getString("name"),DBConnection.rs.getInt("kcal"));//푸시함
		}
		return mv;
	}
	public void setCurrentMove(int kcal) throws SQLException
	{
		ui.updateCurrentMoves(kcal);
	}
	
}
















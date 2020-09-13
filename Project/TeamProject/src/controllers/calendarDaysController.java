package controllers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.*;
import lib.CalendarInfos;
public class calendarDaysController implements Initializable
{
	@FXML private Label lblDay;
	@FXML private Label lblCount;
	@FXML private Label today;
	@FXML private Button add_todo;
	public int day_year,day_month,day_day;
	public void initialize(URL location, ResourceBundle resources)
	{ 
		add_todo.setOnAction(event->add_todo_button());
	}
	public void add_todo_button()
	{
		System.out.println(this.day_year+"년  "+this.day_month+"월  "+this.day_day+"일 "+"일정추가버튼 눌림");
	}
	public void setdate(int year,int month,int day)
	{
		this.day_year = year;
		this.day_month = month;
		this.day_day = day;
	}
	public void setDay(int day)
	{
		String _day = Integer.toString(day);
		lblDay.setText(_day);
	}
	public int getDay()
	{
		String day = lblDay.getText();
		return  Integer.parseInt(day);
	}
	public void setToday()
	{
		today.setText("today");
	}
}

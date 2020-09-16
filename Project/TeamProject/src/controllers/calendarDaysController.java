package controllers;
import java.io.IOException;
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
public class calendarDaysController implements Initializable//캘린더의 각각의 날짜에대한 컨트롤러
{
	@FXML private Label lblDay;//날짜
	@FXML private Label today;//오늘표시
	@FXML private Button add_todo;//일정버튼
	@FXML private Button daypopup_close;
	public Stage popup;//팝업스테이지 변수
	public int day_year,day_month,day_day;//몇년 몇월 몇일
	public void initialize(URL location, ResourceBundle resources)
	{ 
		add_todo.setOnAction(event->add_todo_button());//일정버튼 리스너
	}
	public void add_todo_button()
	{
		System.out.println(this.day_year+"년  "+this.day_month+"월  "+this.day_day+"일 "+"일정추가버튼 눌림");//디버그 베세지
		try {
			FXMLLoader loader = new FXMLLoader();//FXML로더 새로불러옴
			loader.setLocation(getClass().getResource("/Application/dayPopup.fxml"));//팝업fxml불러옴
			Parent root = (Parent) loader.load();//로드함
			Scene scene = new Scene(root);//새로운 씬
			dayPopupController pop = loader.getController();//컨트롤러 등록
			pop.setText(day_year,day_month,day_day);//날짜를 등록함
			Stage stage = new Stage();//새로운 스테이지
			stage.setResizable(false);//크기 재조정불가
			stage.setTitle("일정");//탭 이름은 일정으로 함
			stage.setScene(scene);//씬을 설정
			pop.setStage(stage);//컨트롤러에 스테이지를 전달함.
			stage.show();//보이게함
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
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

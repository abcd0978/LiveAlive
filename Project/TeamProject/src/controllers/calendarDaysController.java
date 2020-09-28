package controllers;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import database.user_days;
import popupcontrollers.dayPopupController;
import popupcontrollers.popup;
public class calendarDaysController implements Initializable//캘린더의 각각의 날짜에대한 컨트롤러
{
	@FXML private Label lblDay;//날짜
	@FXML private Label today;//오늘표시
	@FXML private Button add_todo;//일정버튼
	@FXML private Button daypopup_close;
	private popup pop;
	private user_days ud;
	public int day_year,day_month,day_day;//몇년 몇월 몇일
	public void initialize(URL location, ResourceBundle resources)
	{ 
		add_todo.setOnAction(event->add_todo_button());//일정버튼 리스너
		ud  = new user_days();
	}
	public void add_todo_button()
	{
		System.out.println(this.day_year+"년  "+this.day_month+"월  "+this.day_day+"일 "+"일정추가버튼 눌림");//디버그 베세지
		pop = new popup("일정");
		pop.setLocation("/Application/dayPopup.fxml");
		((dayPopupController)pop.getController()).setText(day_year, day_month, day_day);
		try {
			((dayPopupController)pop.getController()).setUserInfo(Integer.toString(day_year),Integer.toString(day_month),Integer.toString(day_day));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		((dayPopupController)pop.getController()).setInteger(day_year, day_month, day_day);
		pop.show();//보이게함
	}
	public void setdate(int year,int month,int day)
	{
		this.day_year = year;
		this.day_month = month;
		this.day_day = day;
	}
	public void setImportantDay() throws SQLException//날짜의 색을 빨간색으로 바꾸어준다
	{
		if(ud.is_todoDay(day_year, day_month, day_day))
			lblDay.setStyle("-fx-background-color: red");
	}
	public void setDay(int day)//날짜를 지정해준다
	{
		String _day = Integer.toString(day);
		lblDay.setText(_day);
	}
	public int getDay()//날짜를 리턴한다.
	{
		String day = lblDay.getText();
		return  Integer.parseInt(day);
	}
	public void setToday()//날짜에 "today"라는 글씨를 새긴다.
	{
		today.setText("today");
	}
}

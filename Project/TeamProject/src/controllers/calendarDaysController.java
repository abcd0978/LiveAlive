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
public class calendarDaysController implements Initializable//Ķ������ ������ ��¥������ ��Ʈ�ѷ�
{
	@FXML private Label lblDay;//��¥
	@FXML private Label today;//����ǥ��
	@FXML private Button add_todo;//������ư
	@FXML private Button daypopup_close;
	private popup pop;
	private user_days ud;
	public int day_year,day_month,day_day;//��� ��� ����
	public void initialize(URL location, ResourceBundle resources)
	{ 
		add_todo.setOnAction(event->add_todo_button());//������ư ������
		ud  = new user_days();
	}
	public void add_todo_button()
	{
		System.out.println(this.day_year+"��  "+this.day_month+"��  "+this.day_day+"�� "+"�����߰���ư ����");//����� ������
		pop = new popup("����");
		pop.setLocation("/Application/dayPopup.fxml");
		((dayPopupController)pop.getController()).setText(day_year, day_month, day_day);
		try {
			((dayPopupController)pop.getController()).setUserInfo(Integer.toString(day_year),Integer.toString(day_month),Integer.toString(day_day));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		((dayPopupController)pop.getController()).setInteger(day_year, day_month, day_day);
		pop.show();//���̰���
	}
	public void setdate(int year,int month,int day)
	{
		this.day_year = year;
		this.day_month = month;
		this.day_day = day;
	}
	public void setImportantDay() throws SQLException//��¥�� ���� ���������� �ٲپ��ش�
	{
		if(ud.is_todoDay(day_year, day_month, day_day))
			lblDay.setStyle("-fx-background-color: red");
	}
	public void setDay(int day)//��¥�� �������ش�
	{
		String _day = Integer.toString(day);
		lblDay.setText(_day);
	}
	public int getDay()//��¥�� �����Ѵ�.
	{
		String day = lblDay.getText();
		return  Integer.parseInt(day);
	}
	public void setToday()//��¥�� "today"��� �۾��� �����.
	{
		today.setText("today");
	}
}

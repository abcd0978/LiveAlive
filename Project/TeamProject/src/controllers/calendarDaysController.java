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
public class calendarDaysController implements Initializable//Ķ������ ������ ��¥������ ��Ʈ�ѷ�
{
	@FXML private Label lblDay;//��¥
	@FXML private Label today;//����ǥ��
	@FXML private Button add_todo;//������ư
	@FXML private Button daypopup_close;
	public Stage popup;//�˾��������� ����
	public int day_year,day_month,day_day;//��� ��� ����
	public void initialize(URL location, ResourceBundle resources)
	{ 
		add_todo.setOnAction(event->add_todo_button());//������ư ������
	}
	public void add_todo_button()
	{
		System.out.println(this.day_year+"��  "+this.day_month+"��  "+this.day_day+"�� "+"�����߰���ư ����");//����� ������
		try {
			FXMLLoader loader = new FXMLLoader();//FXML�δ� ���κҷ���
			loader.setLocation(getClass().getResource("/Application/dayPopup.fxml"));//�˾�fxml�ҷ���
			Parent root = (Parent) loader.load();//�ε���
			Scene scene = new Scene(root);//���ο� ��
			dayPopupController pop = loader.getController();//��Ʈ�ѷ� ���
			pop.setText(day_year,day_month,day_day);//��¥�� �����
			Stage stage = new Stage();//���ο� ��������
			stage.setResizable(false);//ũ�� �������Ұ�
			stage.setTitle("����");//�� �̸��� �������� ��
			stage.setScene(scene);//���� ����
			pop.setStage(stage);//��Ʈ�ѷ��� ���������� ������.
			stage.show();//���̰���
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

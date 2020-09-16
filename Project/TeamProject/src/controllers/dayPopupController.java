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
public class dayPopupController implements Initializable
{
	@FXML private Button daypopup_close;
    @FXML private Label daypopup_year;
    @FXML private Label daypopup_month;
    @FXML private Label daypopup_date;
    private Stage self;
    private int popup_year,popup_month,popup_date;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		daypopup_close.setOnAction(event->close());
	}
	public void close()
	{
		System.out.println("������ư ����");
		self.close();
	}
	public void setStage(Stage s)//����ʵ忡 �ڽ��� ���������� �����Ѵ�. �׷��� close�Լ��� �ڽ��� ���������� close�Ѵ�.
	{
		this.self = s;
	}
	public void setText(int year,int month,int date)
	{
		popup_year = year;//setText�� �԰� ���ÿ� setDate��
		popup_month = month;
		popup_date = date;
		daypopup_year.setText(Integer.toString(year));
    	daypopup_month.setText(Integer.toString(month));
    	daypopup_date.setText(Integer.toString(date));
	}
	
}

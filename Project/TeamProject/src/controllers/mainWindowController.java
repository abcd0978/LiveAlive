package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lib.CalendarInfos;
import database.*;

public class mainWindowController
{
	@FXML private Label year;
	@FXML private Label month;
	@FXML private Label date;
	@FXML private GridPane GridCal;//�׸����׸��
	@FXML private Button rightB;//��ư
	@FXML private Button leftB;//��ư
	@FXML private Button logout;
	@FXML private AnchorPane anchor_day;
	@FXML private Label user_name;
	private DBConnection db;
	private int __year,__month,__date;//����� �����ϰ� �ϱ����ؼ� ���� �������·� �����س��´�.
	private List<calendarDaysController> daycon;//��¥��Ʈ�ѷ� ����Ʈ
	private Calendar cal;//����ð� �޾ƿ��� ���̺귯��
	private CalendarInfos calinfo;//�޷��� ����� ������ִ� Ŭ����
	public void remove()//��¥����
	{
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
		GridCal.getChildren().remove(8,GridCal.getChildren().size());
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
	}
	public void init(int year,int month)
	{
		daycon = new ArrayList<>();//��Ʈ�ѷ� ����Ʈ
		calinfo = new CalendarInfos(); //�޷°�갴ü
		int firstday = calinfo.firstdate(year, month)%7;
		int lastday = calinfo.leap_date(year, month);
		remove();
			for(int i=1;i<7;i++)
			{
				for(int j=0;j<7;j++)
				{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Application/calendarDays.fxml"));//�޷������� �ҷ���
					 try 
					 {
					    if(i==1 && j<firstday)//�ش� ���� 1�Ϻ��� �޷��� �׸�
					    	continue;
					    else if((((i-1)*7) - (firstday)+j) == lastday)//�ش���� ������ �׸��ԵǸ� �޷��� �׸��׸�.
					    	return;
					    else//������ǿ��� �ɸ��������� �޷��� ����Ѵ�.
					    {
							    AnchorPane day = loader.load();
								GridCal.add(day,j,i);//����� ���� day�� �ִ´�.
								calendarDaysController dc = loader.getController();
								daycon.add(dc);
					    	}
					   }
					 catch (IOException e) 
					 {
							e.printStackTrace();
							System.out.printf("%d��%d�� �׸��µ� ���� �߻� \n",i,j);
					 }
				}
			}
	}
	public void write_date(int year,int month)//������ �Է¹޾� ������ ��Ʈ�ѷ� ����Ʈ�� ��¥�� �������ش�.
	{
		int i = 1;
		int lastday = calinfo.leap_date(year, month);
		for(calendarDaysController day : daycon)
		{
			if(i<=lastday)
			{
				day.setDay(i);
				day.setdate(year, month, i);
				i++;
			}
		}
	}
	public void today()//������ �޷¿��� ǥ�����ִ� �޼ҵ�
	{
		for(calendarDaysController day : daycon)
		{
			if(day.day_day == cal.get(Calendar.DATE) && day.day_month == cal.get(Calendar.MONTH)+1 && day.day_year == cal.get(Calendar.YEAR))
				day.setToday();
		}
	}
	public void initialize() 
	{
		db = new DBConnection();
		rightB.setOnAction(event->increase_date());
		leftB.setOnAction(event->decrease_date());
		logout.setOnAction(event->logout());
		cal = Calendar.getInstance();
		__year = cal.get(Calendar.YEAR);
		year.setText(Integer.toString(__year));
		__month = cal.get(Calendar.MONTH)+1;//MONTHŸ���� 0���� �����ϱ⶧���� 1�� ���������.
		month.setText(Integer.toString(__month));
		__date = cal.get(Calendar.DATE);
		date.setText(Integer.toString(__date));
		init(__year,__month);
		write_date(__year,__month);
		today();
		user_name.setText(db.getName());
	}
	public void logout()//�α׾ƿ�
	{
		System.out.println("�α׾ƿ� ��ư����");
		try {
			Parent login = FXMLLoader.load(getClass().getResource("../Application/root.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage)logout.getScene().getWindow(); // ���� ������ ��������
			primaryStage.setScene(scene);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}//���θ޴��� ���ư���
	}
	public void init_date(int year,int month,int date)//�޷� ��ܿ� �������� ǥ���Ѵ�.
	{
		this.year.setText(Integer.toString(year));
		this.month.setText(Integer.toString(month));
		this.date.setText(Integer.toString(date));
	}
	public void increase_date(){//�����ʹ�ư
		if(__month>=12){
			__month=1;
			__year++;
		}
		else
		{
			__month++;
		}
		init_date(__year,__month,1);
		init(__year,__month);
		write_date(__year,__month);
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		today();
		//System.out.println("last day:"+calinfo.leap_date(__year, __month));
	}
	public void decrease_date()//���ʹ�ư
	{
		if(__month<=1){
			__month=12;
			__year--;
		}
		else{
			__month--;
		}
		init_date(__year,__month,1);
		init(__year,__month);//�޷��� ���κҷ��´�
		write_date(__year,__month);//�޷¿� �ϼ��� ���ش�.
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		today();
		//System.out.println("last day:"+calinfo.leap_date(__year, __month));
	}
}

package controllers;

import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import lib.CalendarInfos;
import database.*;
import lib.*;

public class mainWindowController
{
	@FXML protected Label year;
	@FXML protected Label month;
	@FXML protected Label date;
	@FXML protected GridPane GridCal;//�׸����׸��
	@FXML private Button rightB;//��ư
	@FXML private Button leftB;//��ư
	@FXML private AnchorPane anchor_day;
	public int __year,__month,__date;//����� �����ϰ� �ϱ����ؼ� ���� �������·� �����س��´�.
	protected List<calendarDaysController> daycon;//��¥��Ʈ�ѷ� ����Ʈ
	protected Calendar cal;//����ð� �޾ƿ��� ���̺귯��
	private CalendarInfos calinfo;//�޷��� ����� ������ִ� Ŭ����
	public void remove()//���� �����ȵ�
	{
		System.out.println("number of childrens :"+GridCal.getChildren().size());
		GridCal.getChildren().remove(8,GridCal.getChildren().size());
		System.out.println("number of childrens :"+GridCal.getChildren().size());
		System.out.println("childrens : "+GridCal.getChildren());
	}

	public void init(int year,int month)
	{
		daycon = new ArrayList<>();//��Ʈ�ѷ� ����Ʈ
		calinfo = new CalendarInfos(); //�޷°�갴ü
		int firstday = calinfo.firstdate(year, month);
		int lastday = calinfo.leap_date(year, month);
		remove();
	  //if(GridCal.getChildren().isEmpty())
    	{
			for(int i=1;i<7;i++)
			{
				for(int j=0;j<7;j++)
				{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Application/calendarDays.fxml"));//�޷������� �ҷ���
					 try 
					 {
					    if(i==1 && j<calinfo.firstdate(year, month))//�ش� ���� 1�Ϻ��� �޷��� �׸�
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
	public void today(int _year,int _month,int _day)//������ �޷¿��� ǥ�����ִ� �޼ҵ�
	{
		for(calendarDaysController day : daycon)
		{
			if(day.day_day == _day && day.day_month == _month && day.day_year == _year)
				day.setToday();
		}
	}
	public void initialize() 
	{
		rightB.setOnAction(event->increase_date());
		leftB.setOnAction(event->decrease_date());
		cal = Calendar.getInstance();
		__year = cal.get(Calendar.YEAR);
		year.setText(Integer.toString(__year));
		__month = cal.get(Calendar.MONTH)+1;//MONTHŸ���� 0���� �����ϱ⶧���� 1�� ���������.
		month.setText(Integer.toString(__month));
		__date = cal.get(Calendar.DATE);
		date.setText(Integer.toString(__date));
		init(__year,__month);
		write_date(__year,__month);
		today(__year,__month,__date);
		
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
		//System.out.println("last day:"+calinfo.leap_date(__year, __month));
	}
}

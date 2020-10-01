package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lib.CalendarInfos;
import database.*;
import popupcontrollers.popup;
import popupcontrollers.DailyIntakePopupController;
import popupcontrollers.DailyWorkoutPopupController;

public class mainWindowController
{
	@FXML private Label year;
	@FXML private Label month;
	@FXML private Label date;
	@FXML private GridPane GridCal;//�׸����׸��
	@FXML private Button rightB;//��ư
	@FXML private Button leftB;//��ư
	@FXML private Button logout;
	@FXML private Label tall;//Ű
	@FXML private Label age;//ü��
	@FXML private Label weight;//������
	@FXML private Label sex;//����
	@FXML private Label bmr;//���ʴ�緮
	@FXML private Label user_name;//�̸�
	@FXML private Button daily_intake;//�����˾���ư
	@FXML private Button user_info;//ȸ������ ��ư
	@FXML private Button daily_workout;//��˾���ư
	@FXML private Button refresh;
<<<<<<< HEAD
	private popup workoutPopup;//��˾�
	private popup intakePopup;//�����˾�
	private popup userinfoPopup;//ȸ�������˾�
=======
	@FXML private Button health_info;//�ֽŰǰ�������ư
	private popup workoutPopup;//��˾�
	private popup intakePopup;//�����˾�
	private popup userinfoPopup;//ȸ�������˾�
	private popup healthinfoPopup;//�ֽŰǰ������˾�
>>>>>>> upstream/master
	private int __year,__month,__date;//��������� ��
	private List<calendarDaysController> daycon;//��¥��Ʈ�ѷ� ����Ʈ
	private Calendar cal;//����ð� �޾ƿ��� ���̺귯��
	private CalendarInfos calinfo;//�޷��� ����� ������ִ� Ŭ����
	private userinfo userin;
	private foods fs;
	public void remove()//��¥����
	{
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
		GridCal.getChildren().remove(8,GridCal.getChildren().size());
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
	}
	public String calc_bmr() throws SQLException//userda�� �޼ҵ带 ����ϹǷ� sql����ó���ؾ���
	{
		double result;
		double _tall = Double.parseDouble(userin.getTall());
		int _age = Integer.parseInt(userin.getAge());
		double _weight = Double.parseDouble(userin.getResentWeight());
		int sex=0;
		if(userin.getSex().equals("����"))
			sex=1;
		if(sex==1)
			result = 655.1+(9.56*_weight)+(1.85*_tall)-(4.68*_age);//���� ���ʴ�緮 ����
		else
			result = 66.5+(13.7*_weight)+(5*_tall)-(6.8*_age);//����
		return Double.toString(result);
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
	public void todo_seeker() throws SQLException//���� �̱���
	{
		for(calendarDaysController day : daycon)
			day.setImportantDay();
	}
	public void initialize() throws SQLException 
	{
		userin = new userinfo();
		fs = new foods();
		refresh.setOnAction(event->refresh_action());
		rightB.setOnAction(event->increase_date());//�����޹�ư
		leftB.setOnAction(event->decrease_date());//�����޹�ư
		logout.setOnAction(event->logout());//�α׾ƿ���ư
		user_info.setOnAction(event->user_info());//����� �����Է� ��ư
		daily_intake.setOnAction(event->daily_intake());//����� ���ϼ��뷮 ��ư
		daily_workout.setOnAction(event->daily_workout());//����� ���Ͽ�� ��ư
<<<<<<< HEAD
=======
		health_info.setOnAction(event->health_info());//�ֽ� �ǰ� ���� ��ư
>>>>>>> upstream/master
		cal = Calendar.getInstance();//Ķ���� ��ü
		__year = cal.get(Calendar.YEAR);
		year.setText(Integer.toString(__year));
		__month = cal.get(Calendar.MONTH)+1;//MONTHŸ���� 0���� �����ϱ⶧���� 1�� ���������.
		month.setText(Integer.toString(__month));
		__date = cal.get(Calendar.DATE);
		date.setText(Integer.toString(__date));
		init(__year,__month);//�޷��� �׷�����.
		write_date(__year,__month);//�޷¿� ��¥�� �׷�����.
		today();//�޷¿� ������ ǥ�õǰԲ� ���ش�0
		todo_seeker();//�������ִ³��� ���������� ǥ��
		userin.initUserInfo2();//����ڰ� ���Ͽ� �� ���ø����̼��� ó�� ���ٸ�,ü��,���ϼ��뷮,���Ͽ���� ����Ҽ��ְ����ش�.
		tall.setText(userin.getTall());//����ȭ�鿡 Ű�� �������Ѵ�
<<<<<<< HEAD
		age.setText(userin.getAge());//����ȭ�鿡 ���̰� �������Ѵ�
		sex.setText(userin.getSex());//����ȭ�鿡 ������ �������Ѵ�
=======
		age.setText("�� " + userin.getAge());//����ȭ�鿡 ���̰� �������Ѵ�
		sex.setText((userin.getSex()));//����ȭ�鿡 ������ �������Ѵ�
>>>>>>> upstream/master
		bmr.setText(calc_bmr());//����ȭ�鿡 ���ʴ�緮�� �������Ѵ�.
		weight.setText(userin.getResentWeight());//����ȭ�鿡 �ֱٿ��Է��� ü���� �������Ѵ�.
		user_name.setText(userin.getName());//����ȭ�鿡 ������� �̸��� �������Ѵ�.
	}
<<<<<<< HEAD
=======
	//�Ϸ� �˾�
>>>>>>> upstream/master
	public void daily_workout()
	{
		System.out.println("���ư ����");
		workoutPopup = new popup("�");
		workoutPopup.setLocation("/Application/dailyWorkoutPopup.fxml");
		((DailyWorkoutPopupController)workoutPopup.getController()).setText(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
		workoutPopup.show();
	}
<<<<<<< HEAD
=======
	//�Ϸ缷�� �˾� 
>>>>>>> upstream/master
	public void daily_intake()
	{
		System.out.println("�����ư ����");
		intakePopup = new popup("����");
		intakePopup.setLocation("/Application/dailyIntakePopup.fxml");
		((DailyIntakePopupController)intakePopup.getController()).setText(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
		System.out.println("���� ��Ʈ�ѷ� �ε��");
		intakePopup.show();
	}
<<<<<<< HEAD
=======
	//ȸ������ �˾�
>>>>>>> upstream/master
	public void user_info()
	{
		System.out.println("ȸ������ ��ư ����");
		userinfoPopup = new popup("ȸ������");
		userinfoPopup.setLocation("/Application/enterUserInfoPopup.fxml");
		userinfoPopup.show();
	}
<<<<<<< HEAD
	public void logout()//�α׾ƿ�
	{
=======
	//�ֽŰǰ����� �˾�
	public void health_info() {
		System.out.println("�ֽ� �ǰ� ���� ��ư ����");
		healthinfoPopup = new popup("�ֽ� �ǰ� ����");
		healthinfoPopup.setLocation("/Application/healthInfoPopup.fxml");
		healthinfoPopup.show();
	}
	//�α׾ƿ�
	public void logout() {
>>>>>>> upstream/master
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
<<<<<<< HEAD
	public void init_date(int year,int month,int date)//�޷� ��ܿ� �������� ǥ���Ѵ�.
=======
	//�޷� ��ܿ� �������� ǥ���Ѵ�.
	public void init_date(int year,int month,int date)
>>>>>>> upstream/master
	{
		this.year.setText(Integer.toString(year));
		this.month.setText(Integer.toString(month));
		this.date.setText(Integer.toString(date));
	}
<<<<<<< HEAD
	public void increase_date(){//�����ʹ�ư
=======
	//�޷� �� �����ʹ�ư
	public void increase_date() {
>>>>>>> upstream/master
		if(__month>=12){
			__month=1;
			__year++;
		}
		else
		{
			__month++;
		}
		init_date(__year,__month,1);//�޷����� ǥ�õǾ����� ��¥�� ��
		init(__year,__month);//�޷��� �׸�
		write_date(__year,__month);//�޷¿� ��¥�� ����
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		today();
		try {
			todo_seeker();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	public void decrease_date()//���ʹ�ư
	{
=======
	//�޷� �� ���ʹ�ư
	public void decrease_date() {
>>>>>>> upstream/master
		if(__month<=1){
			__month=12;
			__year--;
		}
		else{
			__month--;
		}
		init_date(__year,__month,1);//�޷����� ǥ�õǾ����� ��¥�� ��
		init(__year,__month);//�޷��� �׸�
		write_date(__year,__month);//�޷¿� ��¥�� ����.
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		today();
		try {
			todo_seeker();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
=======
	//���ΰ�ħ
>>>>>>> upstream/master
	public void refresh_action()
	{
		try
		{
			init(__year,__month);//�޷��� �׷�����.
			write_date(__year,__month);//�޷¿� ��¥�� �׷�����.
			today();//�޷¿� ������ ǥ�õǰԲ� ���ش�0
			todo_seeker();//�������ִ³��� ���������� ǥ��
			tall.setText(userin.getTall());//����ȭ�鿡 Ű�� �������Ѵ�
			age.setText(userin.getAge());//����ȭ�鿡 ���̰� �������Ѵ�
			sex.setText(userin.getSex());//����ȭ�鿡 ������ �������Ѵ�
			bmr.setText(calc_bmr());//����ȭ�鿡 ���ʴ�緮�� �������Ѵ�.
			weight.setText(userin.getResentWeight());//����ȭ�鿡 �ֱٿ��Է��� ü���� �������Ѵ�.
			user_name.setText(userin.getName());//����ȭ�鿡 ������� �̸��� �������Ѵ�.
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

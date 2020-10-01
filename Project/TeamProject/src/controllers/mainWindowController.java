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
	@FXML private GridPane GridCal;//그리드항목들
	@FXML private Button rightB;//버튼
	@FXML private Button leftB;//버튼
	@FXML private Button logout;
	@FXML private Label tall;//키
	@FXML private Label age;//체중
	@FXML private Label weight;//몸무게
	@FXML private Label sex;//성별
	@FXML private Label bmr;//기초대사량
	@FXML private Label user_name;//이름
	@FXML private Button daily_intake;//섭취팝업버튼
	@FXML private Button user_info;//회원정보 버튼
	@FXML private Button daily_workout;//운동팝업버튼
	@FXML private Button refresh;
<<<<<<< HEAD
	private popup workoutPopup;//운동팝업
	private popup intakePopup;//섭취팝업
	private popup userinfoPopup;//회원정보팝업
=======
	@FXML private Button health_info;//최신건강정보버튼
	private popup workoutPopup;//운동팝업
	private popup intakePopup;//섭취팝업
	private popup userinfoPopup;//회원정보팝업
	private popup healthinfoPopup;//최신건강정보팝업
>>>>>>> upstream/master
	private int __year,__month,__date;//계산을위한 수
	private List<calendarDaysController> daycon;//날짜컨트롤러 리스트
	private Calendar cal;//현재시각 받아오는 라이브러리
	private CalendarInfos calinfo;//달력의 계산을 대신해주는 클래스
	private userinfo userin;
	private foods fs;
	public void remove()//날짜지음
	{
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
		GridCal.getChildren().remove(8,GridCal.getChildren().size());
		System.out.println("number of Grid childrens :"+GridCal.getChildren().size());
	}
	public String calc_bmr() throws SQLException//userda의 메소드를 사용하므로 sql예외처리해야함
	{
		double result;
		double _tall = Double.parseDouble(userin.getTall());
		int _age = Integer.parseInt(userin.getAge());
		double _weight = Double.parseDouble(userin.getResentWeight());
		int sex=0;
		if(userin.getSex().equals("여자"))
			sex=1;
		if(sex==1)
			result = 655.1+(9.56*_weight)+(1.85*_tall)-(4.68*_age);//여성 기초대사량 공식
		else
			result = 66.5+(13.7*_weight)+(5*_tall)-(6.8*_age);//남성
		return Double.toString(result);
	}
	public void init(int year,int month)
	{
		daycon = new ArrayList<>();//컨트롤러 리스트
		calinfo = new CalendarInfos(); //달력계산객체
		int firstday = calinfo.firstdate(year, month)%7;
		int lastday = calinfo.leap_date(year, month);
		remove();
			for(int i=1;i<7;i++)
			{
				for(int j=0;j<7;j++)
				{
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/Application/calendarDays.fxml"));//달력의일을 불러옴
					 try 
					 {
					    if(i==1 && j<firstday)//해당 달의 1일부터 달력을 그림
					    	continue;
					    else if((((i-1)*7) - (firstday)+j) == lastday)//해당달의 끝까지 그리게되면 달력을 그만그림.
					    	return;
					    else//어느조건에도 걸리지않으면 달력을 출력한다.
					    {
							    AnchorPane day = loader.load();
								GridCal.add(day,j,i);//각행과 열에 day를 넣는다.
								calendarDaysController dc = loader.getController();
								daycon.add(dc);
					    	}
					   }
					 catch (IOException e) 
					 {
							e.printStackTrace();
							System.out.printf("%d행%d열 그리는데 문제 발생 \n",i,j);
					 }
				}
			}
	}
	public void write_date(int year,int month)//연월을 입력받아 각각의 컨트롤러 리스트에 날짜를 지정하준다.
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
	public void today()//오늘을 달력에서 표시해주는 메소드
	{
		for(calendarDaysController day : daycon)
		{
			if(day.day_day == cal.get(Calendar.DATE) && day.day_month == cal.get(Calendar.MONTH)+1 && day.day_year == cal.get(Calendar.YEAR))
				day.setToday();
		}
	}
	public void todo_seeker() throws SQLException//아직 미구현
	{
		for(calendarDaysController day : daycon)
			day.setImportantDay();
	}
	public void initialize() throws SQLException 
	{
		userin = new userinfo();
		fs = new foods();
		refresh.setOnAction(event->refresh_action());
		rightB.setOnAction(event->increase_date());//다음달버튼
		leftB.setOnAction(event->decrease_date());//이전달버튼
		logout.setOnAction(event->logout());//로그아웃버튼
		user_info.setOnAction(event->user_info());//사용자 정보입력 버튼
		daily_intake.setOnAction(event->daily_intake());//사용자 일일섭취량 버튼
		daily_workout.setOnAction(event->daily_workout());//사용자 일일운동량 버튼
<<<<<<< HEAD
=======
		health_info.setOnAction(event->health_info());//최신 건강 정보 버튼
>>>>>>> upstream/master
		cal = Calendar.getInstance();//캘린더 객체
		__year = cal.get(Calendar.YEAR);
		year.setText(Integer.toString(__year));
		__month = cal.get(Calendar.MONTH)+1;//MONTH타입은 0부터 시작하기때문에 1을 더해줘야함.
		month.setText(Integer.toString(__month));
		__date = cal.get(Calendar.DATE);
		date.setText(Integer.toString(__date));
		init(__year,__month);//달력이 그려진다.
		write_date(__year,__month);//달력에 날짜가 그려진다.
		today();//달력에 오늘이 표시되게끔 해준다0
		todo_seeker();//일정이있는날은 빨간색으로 표시
		userin.initUserInfo2();//사용자가 당일에 이 어플리케이션을 처음 들어갔다면,체중,일일섭취량,일일운동량을 기록할수있게해준다.
		tall.setText(userin.getTall());//메인화면에 키가 나오게한다
<<<<<<< HEAD
		age.setText(userin.getAge());//메인화면에 나이가 나오게한다
		sex.setText(userin.getSex());//메인화면에 성별이 나오게한다
=======
		age.setText("만 " + userin.getAge());//메인화면에 나이가 나오게한다
		sex.setText((userin.getSex()));//메인화면에 성별이 나오게한다
>>>>>>> upstream/master
		bmr.setText(calc_bmr());//메인화면에 기초대사량이 나오게한다.
		weight.setText(userin.getResentWeight());//메인화면에 최근에입력한 체중이 나오게한다.
		user_name.setText(userin.getName());//메인화면에 사용자의 이름이 나오게한다.
	}
<<<<<<< HEAD
=======
	//하루운동 팝업
>>>>>>> upstream/master
	public void daily_workout()
	{
		System.out.println("운동버튼 눌림");
		workoutPopup = new popup("운동");
		workoutPopup.setLocation("/Application/dailyWorkoutPopup.fxml");
		((DailyWorkoutPopupController)workoutPopup.getController()).setText(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
		workoutPopup.show();
	}
<<<<<<< HEAD
=======
	//하루섭취 팝업 
>>>>>>> upstream/master
	public void daily_intake()
	{
		System.out.println("섭취버튼 눌림");
		intakePopup = new popup("섭취");
		intakePopup.setLocation("/Application/dailyIntakePopup.fxml");
		((DailyIntakePopupController)intakePopup.getController()).setText(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE));
		System.out.println("섭취 컨트롤러 로드됨");
		intakePopup.show();
	}
<<<<<<< HEAD
=======
	//회원정보 팝업
>>>>>>> upstream/master
	public void user_info()
	{
		System.out.println("회원정보 버튼 눌림");
		userinfoPopup = new popup("회원정보");
		userinfoPopup.setLocation("/Application/enterUserInfoPopup.fxml");
		userinfoPopup.show();
	}
<<<<<<< HEAD
	public void logout()//로그아웃
	{
=======
	//최신건강정보 팝업
	public void health_info() {
		System.out.println("최신 건강 정보 버튼 눌림");
		healthinfoPopup = new popup("최신 건강 정보");
		healthinfoPopup.setLocation("/Application/healthInfoPopup.fxml");
		healthinfoPopup.show();
	}
	//로그아웃
	public void logout() {
>>>>>>> upstream/master
		System.out.println("로그아웃 버튼눌림");
		try {
			Parent login = FXMLLoader.load(getClass().getResource("../Application/root.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage)logout.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}//메인메뉴로 돌아가기
	}
<<<<<<< HEAD
	public void init_date(int year,int month,int date)//달력 상단에 연월일을 표시한다.
=======
	//달력 상단에 연월일을 표시한다.
	public void init_date(int year,int month,int date)
>>>>>>> upstream/master
	{
		this.year.setText(Integer.toString(year));
		this.month.setText(Integer.toString(month));
		this.date.setText(Integer.toString(date));
	}
<<<<<<< HEAD
	public void increase_date(){//오른쪽버튼
=======
	//달력 월 오른쪽버튼
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
		init_date(__year,__month,1);//달력위에 표시되어지는 날짜를 씀
		init(__year,__month);//달력을 그림
		write_date(__year,__month);//달력에 날짜를 새김
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		today();
		try {
			todo_seeker();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	public void decrease_date()//왼쪽버튼
	{
=======
	//달력 월 왼쪽버튼
	public void decrease_date() {
>>>>>>> upstream/master
		if(__month<=1){
			__month=12;
			__year--;
		}
		else{
			__month--;
		}
		init_date(__year,__month,1);//달력위에 표시되어지는 날짜를 씀
		init(__year,__month);//달력을 그림
		write_date(__year,__month);//달력에 날짜를 새김.
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
	//새로고침
>>>>>>> upstream/master
	public void refresh_action()
	{
		try
		{
			init(__year,__month);//달력이 그려진다.
			write_date(__year,__month);//달력에 날짜가 그려진다.
			today();//달력에 오늘이 표시되게끔 해준다0
			todo_seeker();//일정이있는날은 빨간색으로 표시
			tall.setText(userin.getTall());//메인화면에 키가 나오게한다
			age.setText(userin.getAge());//메인화면에 나이가 나오게한다
			sex.setText(userin.getSex());//메인화면에 성별이 나오게한다
			bmr.setText(calc_bmr());//메인화면에 기초대사량이 나오게한다.
			weight.setText(userin.getResentWeight());//메인화면에 최근에입력한 체중이 나오게한다.
			user_name.setText(userin.getName());//메인화면에 사용자의 이름이 나오게한다.
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

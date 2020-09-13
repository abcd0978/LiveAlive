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
	@FXML protected GridPane GridCal;//그리드항목들
	@FXML private Button rightB;//버튼
	@FXML private Button leftB;//버튼
	@FXML private AnchorPane anchor_day;
	public int __year,__month,__date;//계산을 용이하게 하기위해서 먼저 정수형태로 저장해놓는다.
	protected List<calendarDaysController> daycon;//날짜컨트롤러 리스트
	protected Calendar cal;//현재시각 받아오는 라이브러리
	private CalendarInfos calinfo;//달력의 계산을 대신해주는 클래스
	public void remove()//아직 구현안됨
	{
		System.out.println("number of childrens :"+GridCal.getChildren().size());
		GridCal.getChildren().remove(8,GridCal.getChildren().size());
		System.out.println("number of childrens :"+GridCal.getChildren().size());
		System.out.println("childrens : "+GridCal.getChildren());
	}

	public void init(int year,int month)
	{
		daycon = new ArrayList<>();//컨트롤러 리스트
		calinfo = new CalendarInfos(); //달력계산객체
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
					loader.setLocation(getClass().getResource("/Application/calendarDays.fxml"));//달력의일을 불러옴
					 try 
					 {
					    if(i==1 && j<calinfo.firstdate(year, month))//해당 달의 1일부터 달력을 그림
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
	public void today(int _year,int _month,int _day)//오늘을 달력에서 표시해주는 메소드
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
		__month = cal.get(Calendar.MONTH)+1;//MONTH타입은 0부터 시작하기때문에 1을 더해줘야함.
		month.setText(Integer.toString(__month));
		__date = cal.get(Calendar.DATE);
		date.setText(Integer.toString(__date));
		init(__year,__month);
		write_date(__year,__month);
		today(__year,__month,__date);
		
	}
	public void init_date(int year,int month,int date)//달력 상단에 연월일을 표시한다.
	{
		this.year.setText(Integer.toString(year));
		this.month.setText(Integer.toString(month));
		this.date.setText(Integer.toString(date));
	}
	public void increase_date(){//오른쪽버튼
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
	public void decrease_date()//왼쪽버튼
	{
		if(__month<=1){
			__month=12;
			__year--;
		}
		else{
			__month--;
		}
		init_date(__year,__month,1);
		init(__year,__month);//달력을 새로불러온다
		write_date(__year,__month);//달력에 일수를 써준다.
		System.out.println("GridLines :"+GridCal.isGridLinesVisible());
		//System.out.println("last day:"+calinfo.leap_date(__year, __month));
	}
}

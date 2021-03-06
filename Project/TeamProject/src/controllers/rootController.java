package controllers;

import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.*;

public class rootController implements Initializable {
	@FXML private Button login;
	@FXML private Button signup;
	@FXML private TextField user_id;
	@FXML private PasswordField user_pass;
	public member mb;
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		mb = new member();//db객체를 생성한다.
		login.setOnAction(event->do_action(event));//로그인버튼 이벤트 등록
		signup.setOnAction(event->{//회원가입버튼 이벤트 등록
			try {
				do_signup(event);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		});
	}
	public void do_action(ActionEvent e)//로그인하는 메소드
	{
		System.out.println("login pressed");//디버그메세지
		System.out.println("id: " + user_id.getText());//디버그메세지
		System.out.println("pw: " + user_pass.getText());//디버그메세지
		try {
			if(mb.login(user_id.getText(), user_pass.getText()))
			{
				System.out.println("logined.");
				System.out.println("debug");
			    Parent login_ = FXMLLoader.load(getClass().getResource("../Application/mainWindow.fxml"));// 불러오기
			    Scene scene = new Scene(login_);
			    Stage primaryStage = (Stage)login.getScene().getWindow(); // 현재 윈도우 가져오기
			    primaryStage.setScene(scene);
			}
			else
			{
				System.out.println("login failed.");
			}
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}
	public void do_signup(ActionEvent e)
	{
		try
		{
			System.out.println("dsadsadsadssasd");
		    Parent login = FXMLLoader.load(getClass().getResource("/Application/signup.fxml"));//회원가입창 불러오기
		    Scene scene = new Scene(login);
		    Stage primaryStage = (Stage)signup.getScene().getWindow(); // 현재 윈도우 가져오기
		    primaryStage.setScene(scene);
		 } 
		catch(Exception e1)
		{
		       e1.printStackTrace();
		}
	}
	public String get_id()
	{
		return user_id.getText();
	}
	public String get_pw()
	{
		return user_pass.getText();
	}
}

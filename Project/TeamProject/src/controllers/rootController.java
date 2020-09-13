package controllers;

import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.*;

public class rootController implements Initializable {
	@FXML private Button login;
	@FXML private Button signup;
	@FXML private TextField user_id;
	@FXML private TextField user_pass;
	public DBConnection db;
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		db = new DBConnection();
		login.setOnAction(event->do_action(event));
		signup.setOnAction(event->{
			try 
			{
				do_signup(event);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		});
	}
	public void do_action(ActionEvent e)
	{
		System.out.println("login pressed");
		System.out.println("id: " + user_id.getText());
		System.out.println("pw: " + user_pass.getText());
		try {
			if(db.login(user_id.getText(), user_pass.getText()))
			{
				System.out.println("logined.");
				System.out.println("debug");
			    Parent login_ = FXMLLoader.load(getClass().getResource("/Application/mainWindow.fxml"));// 불러오기
			    Scene scene = new Scene(login_);
			    Stage primaryStage = (Stage)login.getScene().getWindow(); // 현재 윈도우 가져오기
			    primaryStage.setScene(scene);
			}
			else
			{
				System.out.println("login failed.");
			}
		}catch(Exception e1) {
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

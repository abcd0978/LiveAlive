package controllers;

import java.io.IOException;
import java.util.ResourceBundle;

import java.sql.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.*;


public class signupController implements Initializable {
	@FXML private Button create_account;
	@FXML private Button is_duplicate;
	@FXML private Button back;
	@FXML private TextField id_fill;
	@FXML private TextField pw_fill;
	@FXML private TextField pw_refill;
	@FXML private TextField name_fill;
	private boolean is_dup = false;//아이디 중복
	private DBConnection db;
	private PreparedStatement pstmt = null;
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		db = new DBConnection();
		back.setOnAction(event->goBack(event));
		is_duplicate.setOnAction(event->isDuplicate(event));
		create_account.setOnAction(event->createAccount(event));
	}
	public void goBack(ActionEvent e)
	{
		System.out.println("debug message");
		try 
		{
			Parent login = FXMLLoader.load(getClass().getResource("../Application/root.fxml"));
			Scene scene = new Scene(login);
			Stage primaryStage = (Stage)back.getScene().getWindow(); // 현재 윈도우 가져오기
			primaryStage.setScene(scene);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}//메인메뉴로 돌아가기
	}
	public boolean isDuplicate(ActionEvent e)
	{
		return is_dup;
	}
	public void createAccount(ActionEvent e)
	{
		System.out.println("create_account pressed");
		// id, pass, name NULL 확인
		if(!pw_fill.getText().equals(pw_refill.getText())) {
			System.out.println("pw_fill: "+pw_fill.getText() +" pw_refill: "+pw_refill.getText());
			System.out.println("pass differs");
			return;
		}
		System.out.println("id: " + id_fill.getText());
		System.out.println("pw: " + pw_fill.getText());
		System.out.println("name: " + name_fill.getText());
		try {
			db.register(id_fill.getText(), pw_fill.getText(), name_fill.getText());
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
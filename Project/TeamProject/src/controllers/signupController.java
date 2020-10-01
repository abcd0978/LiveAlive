package controllers;

import java.io.IOException;
import java.util.ResourceBundle;
import java.sql.*;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import popupcontrollers.popup;
import database.*;


public class signupController implements Initializable {
	@FXML private Button create_account;
	@FXML private Button is_duplicate;
	@FXML private Button back;
	@FXML private TextField id_fill;//id쓰는칸
	@FXML private TextField pw_fill;//패스워드 쓰는칸
	@FXML private TextField pw_refill;//패스워드 재입력하느칸
	@FXML private TextField name_fill;//이름쓰는칸
	@FXML private Label warning;//경고 라벨
<<<<<<< HEAD
	private boolean dup_test;//중복확인을 했는지 안했는지 확인하는 변수
	private member mb;//member 클래스 매개변수
	private popup inputError;//에러
=======
	@FXML private Label id_dup;//아이디 중복확인 경고
	@FXML private Label not_id_dup;//아이디가 중복되지 않았을 경우 라벨
	@FXML private Label signup_suc;//가입 성공
	private boolean dup_test;//중복확인을 했는지 안했는지 확인하는 변수
	private member mb;//member 클래스 매개변수

>>>>>>> upstream/master
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		mb = new member();
		back.setOnAction(event->goBack(event));
		is_duplicate.setOnAction(event->isDuplicate(event));
		create_account.setOnAction(event->createAccount(event));
		dup_test = false;
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
	//아이디 중복 확인
	public void isDuplicate(ActionEvent e)
	{
		try {
			if(mb.isId_dup(id_fill.getText())) 
			{
				System.out.println("isDuplicate");
<<<<<<< HEAD
				inputError = new popup("중복확인");
				inputError.setLocation("/Application/signupIddupPopup.fxml");
				inputError.show();
=======
				not_id_dup.setText("");
				id_dup.setText("아이디가 중복됩니다.");
>>>>>>> upstream/master
				return;
			}
			else
			{
				dup_test = true;
<<<<<<< HEAD
=======
				id_dup.setText("");
				not_id_dup.setText("사용 가능한 아이디입니다.");
>>>>>>> upstream/master
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	//공백 입력했을 때
	public void InputEmpty() {
<<<<<<< HEAD
		inputError = new popup("입력 오류");
		inputError.setLocation("/Application/signupEmptyPopup.fxml");
		inputError.show();
=======
		warning.setText("  정보를 입력해주십시오.");
>>>>>>> upstream/master
	}
	
	//입력한 두 비밀번호가 일치하지 않을 때
	public void WrongPass() {
<<<<<<< HEAD
		inputError = new popup("입력 오류");
		inputError.setLocation("/Application/signupPasswrongPopup.fxml");
		inputError.show();
=======
		warning.setText("두 비밀번호가 일치하지 않습니다.");
>>>>>>> upstream/master
	}
	public void createAccount(ActionEvent e)
	{
		System.out.println("create_account pressed");
		// id, pass, name NULL 확인
		if(!pw_fill.getText().equals(pw_refill.getText())) {
			System.out.println("pw_fill: "+pw_fill.getText() +" pw_refill: "+pw_refill.getText());
			System.out.println("pass differs");
			WrongPass();
			return;
		}
		//////////////////////////////////패스워드 재입력 일지확인
		if(id_fill.getText().isEmpty() || pw_fill.getText().isEmpty() || name_fill.getText().isEmpty() || pw_refill.getText().isEmpty()) {
			System.out.println("id_fill or pw_fill or pw_refill or name_fill isEmpty");
			InputEmpty();
			return;
		}
		
		System.out.println("id: " + id_fill.getText());
		System.out.println("pw: " + pw_fill.getText());
		System.out.println("name: " + name_fill.getText());
		
		try {
<<<<<<< HEAD
			if(dup_test == true)
				mb.register(id_fill.getText(), pw_fill.getText(), name_fill.getText());
			else
				warning.setText("아이디 중복체크를 해주세요");
=======
			if(dup_test == true && mb.isId_dup(id_fill.getText()) == false) {
				mb.register(id_fill.getText(), pw_fill.getText(), name_fill.getText());
				warning.setText("");
				signup_suc.setText("가입되었습니다. 앱메인으로 돌아가 로그인을 해주십시오.");
			}	
			else
				warning.setText("     아이디 중복체크를 해주십시오.");
>>>>>>> upstream/master
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
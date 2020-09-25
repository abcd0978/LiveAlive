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
import popupcontrollers.popup;
import database.*;


public class signupController implements Initializable {
	@FXML private Button create_account;
	@FXML private Button is_duplicate;
	@FXML private Button back;
	@FXML private TextField id_fill;
	@FXML private TextField pw_fill;
	@FXML private TextField pw_refill;
	@FXML private TextField name_fill;
	private member mb;
	private popup inputError;
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		mb = new member();
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
			Stage primaryStage = (Stage)back.getScene().getWindow(); // ���� ������ ��������
			primaryStage.setScene(scene);
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}//���θ޴��� ���ư���
	}
	//���̵� �ߺ� Ȯ��
	public void isDuplicate(ActionEvent e)
	{
		try {
			if(mb.isId_dup(id_fill.getText())) {
				System.out.println("isDuplicate");
				inputError = new popup("�ߺ��� ���̵�");
				inputError.setLocation("/Application/signupIddupPopup.fxml");
				inputError.show();
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	//���� �Է����� ��
	public void InputEmpty() {
		inputError = new popup("�Է� ����");
		inputError.setLocation("/Application/signupEmptyPopup.fxml");
		inputError.show();
	}
	
	//�Է��� �� ��й�ȣ�� ��ġ���� ���� ��
	public void WrongPass() {
		inputError = new popup("�Է� ����");
		inputError.setLocation("/Application/signupPasswrongPopup.fxml");
		inputError.show();
	}
	public void createAccount(ActionEvent e)
	{
		System.out.println("create_account pressed");
		// id, pass, name NULL Ȯ��
		if(!pw_fill.getText().equals(pw_refill.getText())) {
			System.out.println("pw_fill: "+pw_fill.getText() +" pw_refill: "+pw_refill.getText());
			System.out.println("pass differs");
			WrongPass();
			return;
		}
		
		if(id_fill.getText().isEmpty() || pw_fill.getText().isEmpty() || name_fill.getText().isEmpty() || pw_refill.getText().isEmpty()) {
			System.out.println("id_fill or pw_fill or pw_refill or name_fill isEmpty");
			InputEmpty();
			return;
		}
		
		System.out.println("id: " + id_fill.getText());
		System.out.println("pw: " + pw_fill.getText());
		System.out.println("name: " + name_fill.getText());
		
		try {
			mb.register(id_fill.getText(), pw_fill.getText(), name_fill.getText());
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}

}
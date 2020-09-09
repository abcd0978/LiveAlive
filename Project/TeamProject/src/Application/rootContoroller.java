package Application;

import java.util.ResourceBundle;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class rootContoroller implements Initializable {
	@FXML private Button login;
	@FXML private Button signup;
	@FXML private TextField user_id;
	@FXML private TextField user_pass;
	@Override
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		login.setOnAction(event->do_action(event));
	}
	public void do_action(ActionEvent e)
	{
		System.out.println("login pressed");
	}
}

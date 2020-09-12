package controllers;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import database.*;

public class mainWindowController implements Initializable
{
	@FXML private Label name;
	private DBConnection db;
	private rootController rt;
	@Override 
	public void initialize(java.net.URL loaction, ResourceBundle resources) 
	{
		db = new DBConnection();
		//System.out.println(rt.get_id());
		//System.out.println(rt.get_pw());
	}
}

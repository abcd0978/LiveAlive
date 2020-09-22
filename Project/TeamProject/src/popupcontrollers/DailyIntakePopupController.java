package popupcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

import database.userinfo;
public class DailyIntakePopupController extends closable implements Initializable
{
	@FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
    private userinfo user_i;
    public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		user_i = new userinfo();
	}
    public void setText(int year,int month,int day)
    {
    	this.year.setText(Integer.toString(year));
    	this.month.setText(Integer.toString(month));
    	this.date.setText(Integer.toString(day));
    }
}

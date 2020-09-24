package popupcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DailyWorkoutPopupController extends closable implements Initializable
{
	@FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
    public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		//
	}
    public void setText(int year,int month,int day)
    {
    	this.year.setText(Integer.toString(year));
    	this.month.setText(Integer.toString(month));
    	this.date.setText(Integer.toString(day));
    }
}

package popupcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class userInfoPopupController extends closable implements Initializable
{
	@FXML RadioButton male;
	@FXML RadioButton female;
	@FXML Button saveButton;
	@FXML ToggleGroup sex;
	@FXML TextField tall;
	@FXML TextField age;
	@FXML TextField weight;
	
	
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
	}
}

package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import database.userinfo;

public class userInfoPopupController extends closable implements Initializable
{
	@FXML RadioButton male;//남자버튼
	@FXML RadioButton female;//여자버튼
	@FXML Button saveButton;//저장버튼
	@FXML ToggleGroup sex;//남자여자 선택토글
	@FXML TextField tall;//키
	@FXML TextField age;//나이
	@FXML TextField weight;//체중
	@FXML Label warning;
	private userinfo ui;
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		ui = new userinfo();
		male.setToggleGroup(sex);
		female.setToggleGroup(sex);
		warning.setText("");
		saveButton.setOnAction(event->{
			try {
				save_action();
			} catch (NumberFormatException | SQLException e) {
				warning.setText("입력정보를 확인해주세요");
				e.printStackTrace();
			}
		});
	}
	public int Toggle()
	{
		RadioButton selectedButton = (RadioButton)sex.getSelectedToggle();
		if(selectedButton != null)//선택된 버튼이 있다면
		{
			return Integer.parseInt(selectedButton.getId());//선택된 버튼의 id를 정수로  가져옴
		}
		else
		{
			return 0;
		}
	}
	public void save_action() throws  NumberFormatException, SQLException
	{
			System.out.println("저장버튼 눌림");
			ui.setTall(Double.parseDouble(tall.getText()));
			System.out.println("키설정 됨");
			ui.setAge(Integer.parseInt(age.getText()));
			System.out.println("나이설정 됨");
			ui.setCurrentdayWeight(Double.parseDouble(weight.getText()));
			System.out.println("체중설정 됨");
			ui.setSex(this.Toggle());
			System.out.println("성별설정 됨");	
	}
}

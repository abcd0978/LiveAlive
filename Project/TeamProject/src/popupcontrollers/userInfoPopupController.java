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
	@FXML RadioButton male;//���ڹ�ư
	@FXML RadioButton female;//���ڹ�ư
	@FXML Button saveButton;//�����ư
	@FXML ToggleGroup sex;//���ڿ��� �������
	@FXML TextField tall;//Ű
	@FXML TextField age;//����
	@FXML TextField weight;//ü��
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
				warning.setText("�Է������� Ȯ�����ּ���");
				e.printStackTrace();
			}
		});
	}
	public int Toggle()
	{
		RadioButton selectedButton = (RadioButton)sex.getSelectedToggle();
		if(selectedButton != null)//���õ� ��ư�� �ִٸ�
		{
			return Integer.parseInt(selectedButton.getId());//���õ� ��ư�� id�� ������  ������
		}
		else
		{
			return 0;
		}
	}
	public void save_action() throws  NumberFormatException, SQLException
	{
			System.out.println("�����ư ����");
			ui.setTall(Double.parseDouble(tall.getText()));
			System.out.println("Ű���� ��");
			ui.setAge(Integer.parseInt(age.getText()));
			System.out.println("���̼��� ��");
			ui.setCurrentdayWeight(Double.parseDouble(weight.getText()));
			System.out.println("ü�߼��� ��");
			ui.setSex(this.Toggle());
			System.out.println("�������� ��");	
	}
}

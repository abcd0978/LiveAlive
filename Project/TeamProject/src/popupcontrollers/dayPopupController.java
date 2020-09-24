package popupcontrollers;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import database.user_days;
public class dayPopupController extends closable implements Initializable //팝업 컨트롤러를 상속받는다
{
    @FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
    @FXML private ToggleGroup impt;
    @FXML private RadioButton impt_1;
    @FXML private RadioButton impt_2;
    @FXML private RadioButton impt_3;
    @FXML private RadioButton impt_4;
    @FXML private RadioButton impt_5;
    @FXML private Label warning;
    @FXML private Label todo_label1;
    @FXML private Label todo_label2;
    @FXML private Label todo_label3;
    @FXML private Label todo_label4;
    @FXML private Label todo_label5;
    @FXML private Label todo_label6;
    @FXML private TextField todo_tf;
    @FXML private Button impt_order;
    @FXML private Button alpha_order;
    @FXML private Button saveButton;
    private int __year,__month,__date;
    private user_days ud;
    private String[] todos;
    
//    private Stage self;
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		ud = new user_days();
		warning.setText("");
		impt_1.setToggleGroup(impt);//토글 그룹 설정
		impt_2.setToggleGroup(impt);
		impt_3.setToggleGroup(impt);
		impt_4.setToggleGroup(impt);
		impt_5.setToggleGroup(impt);
		impt_order.setOnAction(event->{try {show_impt();} catch (SQLException e) {e.printStackTrace();}});
		alpha_order.setOnAction(event->{try {show_alpha();} catch (SQLException e) {e.printStackTrace();}});
		saveButton.setOnAction(event->{try {save_action();} catch (SQLException e) {e.printStackTrace();}});
	}
	public int Toggles()
	{
		RadioButton selectedButton = (RadioButton)impt.getSelectedToggle();
		if(selectedButton != null)
		{
			String result = selectedButton.getId();
			return Integer.parseInt(result);
		}
		else
		{
			return 0;
		}
	}
	public void save_action() throws SQLException
	{
		if(Toggles()==0)
		{
			warning.setText("중요도를 선택해주세요");
		}
		else if(todo_tf.getText().isEmpty())
		{
			warning.setText("일정을 입력해주세요");
		}
		else
		{
			System.out.printf("toggle :%d  String: %s",Toggles(),todo_tf.getText());
			warning.setText("");
			ud.saveTodo(todo_tf.getText(),Toggles(),__year,__month,__date);
			show_impt();
		}
	}
	public void show_impt() throws SQLException//중요도순
	{
		System.out.println("중요도순 눌림");
		todos = ud.getTodo_order_by_importance(__year, __month, __date);//지금의 년월일을 받아서 일정을 불러온다
		this.todo_label1.setText(todos[0]);
		this.todo_label2.setText(todos[1]);
		this.todo_label3.setText(todos[2]);
		this.todo_label4.setText(todos[3]);
		this.todo_label5.setText(todos[4]);
		this.todo_label6.setText(todos[5]);
	}
	public void setInteger(int year,int month,int date)
	{
		this.__year = year;
		this.__month =month;
		this.__date = date;
		try 
		{
			show_impt();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	public void show_alpha() throws SQLException//가나다순(알파벳순)
	{
		System.out.println("가나다순 눌림");
		todos = ud.getTodo_order_by_alphabet(__year, __month, __date);//지금의 년월일을 받아서 일정을 불러온다
		this.todo_label1.setText(todos[0]);
		this.todo_label2.setText(todos[1]);
		this.todo_label3.setText(todos[2]);
		this.todo_label4.setText(todos[3]);
		this.todo_label5.setText(todos[4]);
		this.todo_label6.setText(todos[5]);
	}
	public String getYear() 
	{
		return year.getText();
	}
	public void setYear(String year) {
		this.year.setText(year);
	}
	public String getMonth() {
		return month.getText();
	}
	public void setMonth(String month) 
	{
		this.month.setText(month);
	}
	public String getDate() {
		return date.getText();
	}
	public void setDate(String date) {
		this.date.setText(date);
	}
	public void setText(int year,int month,int date)
	{
		this.year.setText(Integer.toString(year));
		this.month.setText(Integer.toString(month));
		this.date.setText(Integer.toString(date));
	}
	public String toString() {
		return year.getText() +"/"+month.getText()+"/"+date.getText();
	}
}

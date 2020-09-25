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
import database.user_days;

public class dayPopupController extends closable implements Initializable //�˾� ��Ʈ�ѷ��� ��ӹ޴´�
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
    @FXML private Button remove;
    private String selected_labelS;
    private Label selected_label;
    private int __year,__month,__date;
    private user_days ud;
    private String[] todos;
   
    
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		ud = new user_days();
		warning.setText("");
		impt_1.setToggleGroup(impt);//��� �׷� ����
		impt_2.setToggleGroup(impt);
		impt_3.setToggleGroup(impt);
		impt_4.setToggleGroup(impt);
		impt_5.setToggleGroup(impt);
		todo_label1.setOnMouseClicked(event->select_label(todo_label1.getText(),todo_label1));
		todo_label2.setOnMouseClicked(event->select_label(todo_label2.getText(),todo_label2));
		todo_label3.setOnMouseClicked(event->select_label(todo_label3.getText(),todo_label3));
		todo_label4.setOnMouseClicked(event->select_label(todo_label4.getText(),todo_label4));
		todo_label5.setOnMouseClicked(event->select_label(todo_label5.getText(),todo_label5));
		todo_label6.setOnMouseClicked(event->select_label(todo_label6.getText(),todo_label6));
		remove.setOnAction(event->{try{remove();}catch(SQLException e1){e1.printStackTrace();}});
		impt_order.setOnAction(event->{try {show_impt();} catch (SQLException e) {e.printStackTrace();}});
		alpha_order.setOnAction(event->{try {show_alpha();} catch (SQLException e) {e.printStackTrace();}});
		saveButton.setOnAction(event->{try {save_action();} catch (SQLException e) {e.printStackTrace();}});
	}
	public int Toggles()
	{
		RadioButton selectedButton = (RadioButton)impt.getSelectedToggle();
		if(selectedButton != null)//���õ� ��ư�� �ִٸ�
		{
			return Integer.parseInt(selectedButton.getId());//���õ� ��ư�� id�� ������  ������
		}
		else
		{
			return 0;
		}
	}
	public void select_label(String todo,Label lb)//Ŭ���� ����
	{
		if(selected_label == null)//�� ������ ���� �ȵǾ��ٸ�
		{
			this.selected_labelS = todo;//��Ʈ���� �����Ѵ�.
			selected_label = lb;//���� �����Ѵ�
			selected_label.setStyle("-fx-background-color: #99FFCC");//���� ������ �����Ѵ�
		}
		else//���õǾ��ٸ�
		{
			selected_label.setStyle(null);
			selected_label = null;
		}
	}
	public void save_action() throws SQLException
	{
		if(Toggles()==0)
		{
			warning.setText("�߿䵵�� �������ּ���");
		}
		else if(todo_tf.getText().isEmpty())
		{
			warning.setText("������ �Է����ּ���");
		}
		else
		{
			System.out.printf("toggle :%d  String: %s",Toggles(),todo_tf.getText());
			warning.setText("");
			ud.saveTodo(todo_tf.getText(),Toggles(),__year,__month,__date);
			show_impt();
		}
	}
	public void remove() throws SQLException
	{
		if(selected_label!=null)
		{
			ud.removeTodo(this.selected_labelS, __year, __month, __date);//��������
			selected_label.setStyle(null);//���� ����
			show_impt();//�ٽñ׷���
		}
	}
	public void show_impt() throws SQLException//�߿䵵��
	{
		System.out.println("�߿䵵�� ����");
		todos = ud.getTodo_order_by_importance(__year, __month, __date);//������ ������� �޾Ƽ� ������ �ҷ��´�
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
	public void show_alpha() throws SQLException//�����ټ�(���ĺ���)
	{
		System.out.println("�����ټ� ����");
		todos = ud.getTodo_order_by_alphabet(__year, __month, __date);//������ ������� �޾Ƽ� ������ �ҷ��´�
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

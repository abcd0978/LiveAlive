package popupcontrollers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import database.food;
import database.foods;
import database.userinfo;
import lib.binarySearch;
import lib.selctionSort;
public class DailyIntakePopupController extends closable implements Initializable
{
	@FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
    @FXML private Label status;
    @FXML private TextField searchBar;
    @FXML private TableView<food> foodtable;
    @FXML private TableColumn<food, String> name;//�̸�
    @FXML private TableColumn<food, Integer> serve;//1ȸ������
    @FXML private TableColumn<food, String> unit;//����
    @FXML private TableColumn<food, Double> kcal;//����
    @FXML private TableColumn<food, Double> carb;//ź��ȭ��
    @FXML private TableColumn<food, Double> protein;//�ܹ���
    @FXML private TableColumn<food, Double> fat;//����
    @FXML private Button Sort_Kcal;
    @FXML private Button Sort_Carb;
    @FXML private Button Sort_Prot;
    @FXML private Button Sort_Fat;
    @FXML private Button searchButton;
    @FXML private Button saveButton;
    private userinfo ui;
    private selctionSort ss;
    private binarySearch bs;
    private food[] fd;
    private foods fds;
    private ObservableList<food> foods;
    public void initialize(URL location, ResourceBundle resources) 
    {
		super.initialize(location, resources);
		ss = new selctionSort();//�����Ǽ�Ʈ
		bs = new binarySearch();
		ui = new userinfo();
		fds = new foods();//���İ�ü ����ó��Ŭ����
		try {fd = fds.setFoodInfos();}catch(SQLException e){e.printStackTrace();}
		foods = FXCollections.observableArrayList(fd);
		foodtable.setItems(foods);
		Sort_Kcal.setOnAction(event->{try{Sort(1);}catch(SQLException e){e.printStackTrace();}});
		Sort_Carb.setOnAction(event->{try{Sort(2);}catch(SQLException e){e.printStackTrace();}});
		Sort_Prot.setOnAction(event->{try{Sort(3);}catch(SQLException e){e.printStackTrace();}});
		Sort_Fat.setOnAction(event->{try{Sort(4);}catch(SQLException e){e.printStackTrace();}});
		searchButton.setOnAction(event->Search());
		saveButton.setOnAction(event->{try{saveAction();}catch(SQLException e){e.printStackTrace();}});
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		kcal.setCellValueFactory(new PropertyValueFactory<>("Kcal"));
		unit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
		carb.setCellValueFactory(new PropertyValueFactory<>("Carb"));
		serve.setCellValueFactory(new PropertyValueFactory<>("Serv"));
		protein.setCellValueFactory(new PropertyValueFactory<>("Protein"));
		fat.setCellValueFactory(new PropertyValueFactory<>("Fat"));
	}
	public void Sort(int bywhat) throws SQLException
    {
		this.fd = fds.SLSortBy(this.fd,bywhat);
    	foods = FXCollections.observableArrayList(this.fd);
    	foodtable.setItems(foods);
    }
	public void Search()
	{
		food temp = null;
		String str = this.searchBar.getText();
		temp = bs.binary_food(fd, str, 5);
		foods = FXCollections.observableArrayList(temp);
		foodtable.setItems(foods);
	}
	public void saveAction() throws SQLException
	{
		if(foodtable.getSelectionModel().getSelectedItem()!=null)
		{
			ui.updateCurrentIntake(foodtable.getSelectionModel().getSelectedItem().getKcal());//������ Į�θ��� �����Ѵ�.
			status.setText(ui.getName()+"�� "+month.getText()+"��"+date.getText()+"�� "+foodtable.getSelectionModel().getSelectedItem().getKcal()+"Į�θ� �߰�");
		}		
		else
		{
			status.setText("�������ּ���");
			status.setStyle("-fx-background-color: #FF4500");
		}
	}
    public void setText(int year,int month,int day)
    {
    	this.year.setText(Integer.toString(year));
    	this.month.setText(Integer.toString(month));
    	this.date.setText(Integer.toString(day));
    }
}

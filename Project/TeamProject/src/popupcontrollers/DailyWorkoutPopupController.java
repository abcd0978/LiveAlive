package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.food;
import database.move;
import database.moves;
import database.userinfo;
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

public class DailyWorkoutPopupController extends closable implements Initializable
{
	@FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
    @FXML private Label status;
    @FXML private Button sort_kcal;
    @FXML private Button update;
    @FXML private Button saveButton;
    @FXML private Button remove;
    @FXML private TextField name_T;
    @FXML private TextField kcal_T;
    @FXML private TableView<move> movetable;
    @FXML private TableColumn<move,String> name;
    @FXML private TableColumn<move,Integer> kcal;
    private moves mvs;
    private move[] mv;
    private userinfo ui;
    private ObservableList<move> mooves;
    public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		mvs = new moves();
		ui = new userinfo();
		try {mv=mvs.setMoveInfo();}catch(SQLException e){e.printStackTrace();}
		mooves = FXCollections.observableArrayList(mv);
		sort_kcal.setOnAction(event->sort());//
		update.setOnAction(event->update());//����
		saveButton.setOnAction(event->{try{save();}catch(SQLException e){e.printStackTrace();}});//����
		remove.setOnAction(event->remove());//����
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kcal.setCellValueFactory(new PropertyValueFactory<>("kcal"));
		movetable.setItems(mooves);
	}
    public void setText(int year,int month,int day)//������ ��¥�� �����Ѵ�.
    {
    	this.year.setText(Integer.toString(year));
    	this.month.setText(Integer.toString(month));
    	this.date.setText(Integer.toString(day));
    }
    public void sort()//���������� �����Ѵ�.
    {
    	mooves = FXCollections.observableArrayList(mv);
		movetable.setItems(mooves);
    }
    public void update()//���� ��� �ִ´�
    {
    	if(!mvs.isFull())//���� �����ʾҴٸ�
    		mvs.push(name_T.getText(),Integer.parseInt(kcal_T.getText()));//pop�� ������.
    	mv = mvs.get();//���� ��� �޾ƿ´�
    	mooves = FXCollections.observableArrayList(mv);//��� ����Ʈ�� �ִ´�
		movetable.setItems(mooves);//����� ���̰��Ѵ�.
    }
    public void save() throws SQLException//������ ����� �����Ѵ�.
    {
     	mvs.setCurrentMove(movetable.getSelectionModel().getSelectedItem().getKcal());
     	status.setText(ui.getName()+"�� "+movetable.getSelectionModel().getSelectedItem().getName()+"� "+movetable.getSelectionModel().getSelectedItem().getKcal()+"Į�θ� �Ҹ�");
    }
    public void remove()//�����Ѵ�.
    {
    	if(!mvs.isEmpty())//������� �ʴٸ�
    		mvs.pop();//pop�� �����Ѵ�.
    	mooves = FXCollections.observableArrayList(mv);
    	mv = mvs.get();
		movetable.setItems(mooves);
    }
    
}

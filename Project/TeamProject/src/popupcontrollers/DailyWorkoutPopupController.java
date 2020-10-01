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
		update.setOnAction(event->update());//구현
		saveButton.setOnAction(event->{try{save();}catch(SQLException e){e.printStackTrace();}});//구현
		remove.setOnAction(event->remove());//구현
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		kcal.setCellValueFactory(new PropertyValueFactory<>("kcal"));
		movetable.setItems(mooves);
	}
    public void setText(int year,int month,int day)//오늘의 날짜를 기입한다.
    {
    	this.year.setText(Integer.toString(year));
    	this.month.setText(Integer.toString(month));
    	this.date.setText(Integer.toString(day));
    }
    public void sort()//열량순으로 정렬한다.
    {
    	mooves = FXCollections.observableArrayList(mv);
		movetable.setItems(mooves);
    }
    public void update()//새로 운동을 넣는다
    {
    	if(!mvs.isFull())//가득 차지않았다면
    		mvs.push(name_T.getText(),Integer.parseInt(kcal_T.getText()));//pop을 수행함.
    	mv = mvs.get();//새로 운동을 받아온다
    	mooves = FXCollections.observableArrayList(mv);//운동을 리스트에 넣는다
		movetable.setItems(mooves);//운동들을 보이게한다.
    }
    public void save() throws SQLException//오늘한 운동으로 지정한다.
    {
     	mvs.setCurrentMove(movetable.getSelectionModel().getSelectedItem().getKcal());
     	status.setText(ui.getName()+"님 "+movetable.getSelectionModel().getSelectedItem().getName()+"운동 "+movetable.getSelectionModel().getSelectedItem().getKcal()+"칼로리 소모");
    }
    public void remove()//삭제한다.
    {
    	if(!mvs.isEmpty())//비어있지 않다면
    		mvs.pop();//pop을 수행한다.
    	mooves = FXCollections.observableArrayList(mv);
    	mv = mvs.get();
		movetable.setItems(mooves);
    }
    
}

package popupcontrollers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.health_info;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;

public class HealthInfoController extends closable implements Initializable {
	
	@FXML Pagination pagination;
	@FXML Label title1; @FXML Label title2; @FXML Label title3;
	@FXML Label text1; @FXML Label text2; @FXML Label text3;
	private health_info hi;
	private final ChangeListener<Number> paginationChangeListener = (observable, oldValue, newValue) -> changePage();
	
	public void initialize(URL location, ResourceBundle resources) 
	{
		hi = new health_info();
		
		super.initialize(location, resources);
	
		int numberOfData; // 데이터베이스에 저장된 데이터 수
		
		
		try {
			numberOfData = hi.numberofhealthInfoData();
			
			pagination.setPageCount((int) Math.ceil((double)numberOfData/3.0));
			title1.setText(hi.getTitle(numberOfData));
			text1.setText(hi.getText(numberOfData));
			title2.setText(hi.getTitle(numberOfData-1));
			text2.setText(hi.getText(numberOfData-1));
			title3.setText(hi.getTitle(numberOfData-2));
			text3.setText(hi.getText(numberOfData-2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pagination.currentPageIndexProperty().addListener(paginationChangeListener);
	}
	
	public void changePage() {
		// text1.setText(String.format("Current Page: %d", pagination.getCurrentPageIndex()));
		int CurrentPageNum = pagination.getCurrentPageIndex()+1; // 0부터 시작
		System.out.println("Current Page : " + CurrentPageNum);
		
		hi = new health_info();
		try {
			int numberOfData = hi.numberofhealthInfoData();
			int dataNum = numberOfData-(CurrentPageNum-1)*3;
			
			title1.setText(hi.getTitle(dataNum));
			text1.setText(hi.getText(dataNum));
			title2.setText(hi.getTitle(dataNum-1));
			text2.setText(hi.getText(dataNum-1));
			title3.setText(hi.getTitle(dataNum-2));
			text3.setText(hi.getText(dataNum-2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}

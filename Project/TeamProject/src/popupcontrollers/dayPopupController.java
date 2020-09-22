package popupcontrollers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class dayPopupController extends closable implements Initializable //팝업 컨트롤러를 상속받는다
{
    @FXML private Label year;
    @FXML private Label month;
    @FXML private Label date;
//    private Stage self;

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		super.initialize(location, resources);
		//
	}
	/**
	 * @return the year
	 */
	public String getYear() 
	{
		return year.getText();
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year.setText(year);
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month.getText();
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) 
	{
		this.month.setText(month);
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date.getText();
	}
	/**
	 * @param date the date to set
	 */
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

/**
 * 
 */
package popupcontrollers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public abstract class closable implements Initializable {

	@FXML protected Button closeButton;
	protected Stage stage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		closeButton.setOnAction(event->close());
	}
	public void close() {
		System.out.println("close pressed.");
		this.stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}

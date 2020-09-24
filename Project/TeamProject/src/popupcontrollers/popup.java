/**
 * 
 */
package popupcontrollers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import lib.fxmlHandler;
import database.DBConnection;
/**
 * @author abcd0
 *
 */
public class popup {
	protected fxmlHandler handler;
	protected Initializable controller;
	protected Stage window;
	protected DBConnection db;
	
	public popup(String title) 
	{
		window = new Stage();
		window.setTitle(title);
		window.setResizable(false);
	}
	public popup(String title, boolean resizeable) 
	{
		window = new Stage();
		window.setTitle(title);
		window.setResizable(resizeable);
	}
	public void setLocation(String location) 
	{
		handler = new fxmlHandler(location);
		controller = handler.getController();
		((closable)controller).setStage(window);
		window.setScene(handler.getScene());
	}
	public Initializable getController() 
	{
		return this.controller;
	}
	
	public void show() {
		window.show();
	}
}

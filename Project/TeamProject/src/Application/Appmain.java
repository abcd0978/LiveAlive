package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Appmain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("root.fxml"));
		Scene scene = new Scene(root); // Scene 객체 생성
		primaryStage.setTitle("살자, 건강하게.");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String args[]) {
		launch(args);
	}
}

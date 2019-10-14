package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root =FXMLLoader.load(getClass().getResource("/view/index.fxml"));
			Scene scene = new Scene(root,385,224);
			scene.getStylesheets().add(getClass().getResource("/assets/application.css").toExternalForm());
			primaryStage.setTitle("Shutdown Timer");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

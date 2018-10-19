package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
//			BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login_screen.fxml"));
			primaryStage.setTitle("Login");
			primaryStage.setScene(new Scene(root,700,400));
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

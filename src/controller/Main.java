package controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage palco;
    private static Scene sceneLogin;
    private static Scene sceneMenu;
    private static Scene sceneMesa;
    private static Scene sceneCliente;
    private static Scene sceneCardapio;
    private static Scene sceneProfile;
    private static Scene sceneFidelidade;
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		palco = primaryStage;
		
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/Login_screen.fxml"));
		sceneLogin = new Scene(fxmlLogin);
		
		palco.setTitle("IINV Bar");
		palco.setScene(sceneLogin);
		palco.setResizable(false);
		palco.show();
//			Parent root = FXMLLoader.load(getClass().getResource("../view/Login_screen.fxml"));
//			primaryStage.setResizable(false);
//			primaryStage.setTitle("Login");
//			primaryStage.setScene(new Scene(root,700,400));
//			primaryStage.show();
	}
	
	public static void loadMenu() throws Exception{
        Parent fxmlMenu = FXMLLoader.load(Main.class.getResource("../view/Menu_screen.fxml"));
        sceneMenu = new Scene(fxmlMenu);
    }
	
	public static void loadCliente() throws Exception{
        Parent fxmlCliente = FXMLLoader.load(Main.class.getResource("../view/Cliente_screen.fxml"));
        sceneCliente = new Scene(fxmlCliente);
    }
	
	public static void loadCardapio() throws Exception{
        Parent fxmlCardapio = FXMLLoader.load(Main.class.getResource("../view/Cardapio_screen.fxml"));
        sceneCardapio = new Scene(fxmlCardapio);
    }
	
	public static void loadMesa() throws Exception{
        Parent fxmlMesa = FXMLLoader.load(Main.class.getResource("../view/Mesa_screen.fxml"));
        sceneMesa = new Scene(fxmlMesa);
    }
	
	public static void loadProfile() throws Exception{
        Parent fxmlProfile = FXMLLoader.load(Main.class.getResource("../view/Funcionario_screen.fxml"));
        sceneProfile = new Scene(fxmlProfile);
    }
	
	public static void loadFidelidade() throws Exception{
        Parent fxmlFidelidade = FXMLLoader.load(Main.class.getResource("../view/Fidelidade_screen.fxml"));
        sceneFidelidade = new Scene(fxmlFidelidade);
    }
	
	public static void sceneChange(String cena) throws Exception {
        switch (cena){
            case "sceneLogin":
                palco.setScene(sceneLogin);
                break;
            case "sceneMenu":
                loadMenu();
                palco.setScene(sceneMenu);
                palco.centerOnScreen();
                break;
            case "sceneCliente":
                loadCliente();
                palco.setScene(sceneCliente);
                palco.centerOnScreen();
                break;
            case "sceneCardapio":
                loadCardapio();
                palco.setScene(sceneCardapio);
                palco.centerOnScreen();
                break;
            case "sceneMesa":
                loadMesa();
                palco.setScene(sceneMesa);
                palco.centerOnScreen();
                break;
            case "sceneProfile":
                loadProfile();
                palco.setScene(sceneProfile);
                palco.centerOnScreen();
                break;
            case "sceneFidelidade":
                loadFidelidade();
                palco.setScene(sceneFidelidade);
                palco.centerOnScreen();
                break;
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}

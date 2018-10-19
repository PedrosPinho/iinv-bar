package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginController {

    @FXML
    private TextField tfRegistro;

    @FXML
    private Label lblCadastro;

    @FXML
    private Button btnEntrar;
 
    @FXML
    public void login () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Menu_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.show();
    }
    
    @FXML
    public void cadastro () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Funcionario_cadastro_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Cadastro");
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.show();
    }

}

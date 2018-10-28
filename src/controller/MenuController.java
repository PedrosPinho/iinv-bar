package controller;

import java.io.IOException;

import classs.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {

	
    @FXML
    private Button btnFidelidade;

    @FXML
    private Button btnMesa;

    @FXML
    private Button btnPerfil;

    @FXML
    private Label lblSair;

    @FXML
    private Button btnCliente;

    @FXML
    private Label lblData;

    @FXML
    private Button btnCardapio;

    @FXML
    public void client () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Cliente_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void mesa () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Mesa_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void cardapio () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Cardapio_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
//    @FXML NAO SEI HEHEHE
//    public void logoff () throws IOException {
//		this.close();
//    }
    
    @FXML
    public void profile () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Funcionario_cadastro_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void fidelidade () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Fidelidade_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }

}
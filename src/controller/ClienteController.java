package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClienteController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Label lblFiltrar;

    @FXML
    private TextField tfFiltrar;

    @FXML
    private TableView<?> tbCliente;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRelatorio;

    @FXML
    private Label lblVoltar;

    @FXML
    private Button btnRemover;
    
    @FXML
    public void cadastro () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Cliente_cadastro_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.show();
    }

}
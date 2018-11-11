package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ContaController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField tfItens;

    @FXML
    private Label lblBuscar;

    @FXML
    private Button btnFechar;

    @FXML
    private Label lblQtd;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField tfBuscar;

    @FXML
    private TextField tfQtd;

    @FXML
    private Label lblItens;
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

}

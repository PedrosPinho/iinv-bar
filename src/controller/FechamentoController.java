package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FechamentoController {

    @FXML
    private Label lblFechamento;

    @FXML
    private Label lblCpf;

    @FXML
    private TextField tfTotal;

    @FXML
    private Button btnCadastrado;

    @FXML
    private TextField tfFinal;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Label lblMesa;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblDividir;

    @FXML
    private TextField tfCpf;

    @FXML
    private Label lblFinal;

    @FXML
    private Button btnPagar;

    @FXML
    private TextField tfDividir;

    @FXML
    private Button btnNCadastrado;
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

}
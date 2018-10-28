package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastroFuncController {

    @FXML
    private Label lblCpf;

    @FXML
    private Label lblFuncao;

    @FXML
    private TextField tfInicio;

    @FXML
    private Label lblRegistro;

    @FXML
    private TextField tfRegistro;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField tfCpf;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblNome;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lblInicio;

    @FXML
    private TextField tfFuncao;

    @FXML
    public void create () throws IOException {
    	JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("cpf", this.tfCpf.getText());
        jsonObject.put("email", this.tfEmail.getText());
        jsonObject.put("funcao", this.tfFuncao.getText());
        jsonObject.put("inicio", this.tfInicio.getText());
        jsonObject.put("type", "funcionario");
        
    	String uri = "http://localhost:5000/iinv-bar/us-central1/users/create";
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();
    	
    	if(connection.getResponseCode() == 200) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro tops");
			alert.setHeaderText("tops");
			alert.setContentText("tops");
	
			alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro nao tops");
			alert.setHeaderText("nao tops");
			alert.setContentText("Saco de coco, talquei?");
	
			alert.showAndWait();
    	}
    }
}
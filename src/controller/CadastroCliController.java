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
import javafx.stage.Stage;

public class CadastroCliController {

    @FXML
    private Label lblCPF;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfCpf;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField tfEmail;

    @FXML
    private Label lblNome;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField tfNome;
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void create () throws IOException {
    	JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("cpf", this.tfCpf.getText());
        jsonObject.put("email", this.tfEmail.getText());
        jsonObject.put("type", "cliente");
        
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/users/create";
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


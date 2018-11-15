package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import classs.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroCliController {
	private String modo;

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
    	
    	//if(this.modo == "create") {
    		
    	
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("cpf", this.tfCpf.getText());
        jsonObject.put("email", this.tfEmail.getText());      
        jsonObject.put("type", "cliente");
       /* }
    	else {
    		Cliente cliente = new Cliente();
    		cliente.setCpf(tfCpf.getText());
    		cliente.setNome(tfNome.getText());
    		cliente.setEmail(tfEmail.getText());
    		cliente.setTelefone(tfTelefone.getText());
    		
    		Gson gson = new Gson();
            jsonObject.put("user", gson.toJson(cliente));
            jsonObject.put("type", "cliente");

    	}*/
        
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/users/"+ this.modo;
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();
    	
    	System.out.println(connection.getResponseMessage());
    	
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
			alert.setContentText("Erro");
			alert.showAndWait();
    	}
    }

	public void carregaCliente(Cliente cliente) {
		
		if(cliente.getCpf()== null) {
			this.modo = "create";
			tfCpf.setDisable(false);
		}else{
			this.modo = "alter";
		tfCpf.setText(cliente.getCpf());
		tfTelefone.setText(cliente.getTelefone());
		tfEmail.setText(cliente.getEmail());
		tfNome.setText(cliente.getNome());
		
		tfCpf.setDisable(true);
		}
		
	}



}


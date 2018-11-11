package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
 
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import classs.Funcionario;

public class LoginController {

    @FXML
    private TextField tfRegistro;

    @FXML
    private Label lblCadastro;

    @FXML
    private Button btnEntrar;
 
    @FXML
    public void login () throws IOException, ParseException, InterruptedException {
		this.request();
    }
    public Boolean a (){this.btnEntrar.setText("Aguarde..."); return true;}
    	
    
    public void request ()  throws IOException, ParseException, InterruptedException {
    	JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("cpf", this.tfRegistro.getText());
        
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/users/login";
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();

    	if (connection.getResponseCode() == 200) {
    			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Menu_screen.fxml"));
	
	    	Scene scene = new Scene(root);
			
			Stage stage = new Stage();
	
			stage.setTitle("Menu");
			stage.setUserData(this.tfRegistro.getText());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erro no login");
    		alert.setHeaderText("Saco de coco, talquei?");
    		alert.setContentText("mt problema mermao");

    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void cadastro () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Funcionario_cadastro_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Cadastro");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }

}

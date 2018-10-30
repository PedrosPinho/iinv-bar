package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CardapioController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfFiltrar;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lvlVoltar;

    @FXML
    private TableView<?> tbCardapio;

    @FXML
    private Label lblPreco;

    @FXML
    private Label lblFiltrar;

    @FXML
    private TextField tfDesc;

    @FXML
    private Label lblItem;

    @FXML
    private Label lblNome;

    @FXML
    private Button btnRemover;

    @FXML
    private TextField tfNome;
    
    @FXML
    public void addItem () throws IOException {
JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("descricao", this.tfDesc.getText());
        jsonObject.put("preco", this.tfPreco.getText());
        
    	String uri = "http://localhost:5000/iinv-bar/us-central1/cardapio/create";
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
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Deu bom");
    		alert.setHeaderText("Deu bom, talquei?");
    		alert.setContentText("sem problema mermao");

    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("tops");
    		alert.setHeaderText("Deu ruim, talquei?");
    		alert.setContentText("mt problema mermao");

    		alert.showAndWait();
    	}

    }

}

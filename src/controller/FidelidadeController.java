package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Cardapio;
import classs.Cliente;
import classs.Desconto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FidelidadeController {
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("https://us-central1-iinv-bar.cloudfunctions.net/users/client");
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoInput(true);
		connection.setRequestProperty("Content-type", "application/json, charset=utf-8");
		connection.setDoOutput(true);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write("sofuncionouassimnaomejulgue");
        outputStreamWriter.flush();
    	BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
    	org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
    	JSONArray obj = (JSONArray) parse.parse(in.readLine());
    	
    	
    	for(Object a : obj) {
    		al.add(a);
    	}
    	Gson gson = new Gson();
    	ObservableList<Cliente> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Cliente c = gson.fromJson(a.toString(), Cliente.class);
    		data.add(c);
    	});
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
    	this.tcCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
    	this.tcTel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefone"));
    	this.tcFreq.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Frequencia"));	
    	
    	this.tbFidelidade.getItems().setAll(data);

    	this.getFid();
    	in.close();
	}
	
	public void getFid() throws IOException, ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("https://us-central1-iinv-bar.cloudfunctions.net/mesa/fidelidade");
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("GET");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
    	org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
    	JSONArray obj = (JSONArray) parse.parse(in.readLine());
    	
    	
    	for(Object a : obj) {
    		al.add(a);
    	}
    	Gson gson = new Gson();
    	ObservableList<Desconto> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Desconto c = gson.fromJson(a.toString(), Desconto.class);
    		data.add(c);
    		this.lblDesconto.setText(Integer.toString(data.get(0).getPorcentagem()) + "%");
    		this.lblFrequencia.setText(Integer.toString(data.get(0).getFrequencia()));

    	});
	}
	
    @FXML
    public void voltar() throws Exception {
    	Main.sceneChange("sceneMenu");
    }

    @FXML
    private Label lblDesconto;

    @FXML
    private TableColumn<Cliente, String> tcFreq;

    @FXML
    private Button btnDesconto;

    @FXML
    private TableColumn<Cliente, String> tcNome;

    @FXML
    private TableView<Cliente> tbFidelidade;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField tfDesconto;

    @FXML
    private Label lblFrequencia;

    @FXML
    private TextField tfFrequencia;

    @FXML
    private TableColumn<Cliente, String> tcCpf;

    @FXML
    private TableColumn<Cliente, String> tcTel;
    

    @FXML
    public void click () throws IOException, ParseException {
    	JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("frequencia", this.tfFrequencia.getText());
        jsonObject.put("desconto", this.tfDesconto.getText());
    	
        String uri = "https://us-central1-iinv-bar.cloudfunctions.net/mesa/fidelidade";
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
			alert.setTitle("Sucesso");
			alert.setHeaderText("Desconto criado");
			alert.setContentText("OK =)");
	
			alert.showAndWait();
			this.getFid();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erro");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Erro");
			alert.showAndWait();
    	}
    }

}
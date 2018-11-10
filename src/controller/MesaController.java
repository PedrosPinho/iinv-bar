package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;

import com.google.gson.Gson;

import classs.Cliente;
import classs.Mesa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MesaController {

	@SuppressWarnings("unchecked")
	public MesaController() throws IOException, ParseException, org.json.simple.parser.ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("https://us-central1-iinv-bar.cloudfunctions.net/mesa/");
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
    	ObservableList<Mesa> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Mesa c = gson.fromJson(a.toString(), Mesa.class);
    		data.add(c);
    	});
    	
    	this.tcMesa.setCellValueFactory(new PropertyValueFactory<Mesa, String>("num_mesa"));
    	this.tcPess.setCellValueFactory(new PropertyValueFactory<Mesa, String>("num_pessoas"));

    	this.tbMesas.getItems().setAll(data);

    	in.close();
		
	}

    @FXML
    private TableView<Mesa> tbMesas;
    

    @FXML
    private TableColumn<Mesa,String> tcMesa;
    

    @FXML
    private TableColumn<Mesa,String> tcPess;

    @FXML
    private Label lblVoltar;

}

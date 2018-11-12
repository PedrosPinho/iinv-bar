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

import classs.Cardapio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ContaController {
	private int numero;
	private double total;
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException, org.json.simple.parser.ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("http://localhost:5000/iinv-bar/us-central1/mesa/itens/" + Integer.toString(numero));
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
    	ObservableList<Cardapio> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Cardapio c = gson.fromJson(a.toString(), Cardapio.class);
    		this.total += (c.getPreco() * c.getQuantidade());
    		data.add(c);
    	});
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("id"));
    	this.tcDescricao.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("nome"));
    	this.tcPreco.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Preco"));
    	this.tcQtd.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Quantidade"));

    	this.tbCardapio.getItems().setAll(data);
    	

//    		ArrayList<Object> al1 = new ArrayList();
//    		URL url1 = new URL("https://us-central1-iinv-bar.cloudfunctions.net/cardapio/");
//        	HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
//        	connection1.setRequestMethod("GET");
//        	connection1.setDoOutput(true);
//        	connection1.setDoInput(true);
//        	
//        	BufferedReader in1 = new BufferedReader(
//    		        new InputStreamReader(connection1.getInputStream()));
//        	org.json.simple.parser.JSONParser parse1 = new org.json.simple.parser.JSONParser();
//        	JSONArray obj1 = (JSONArray) parse1.parse(in1.readLine());
//        	
//        	
//        	for(Object a : obj1) {
//        		al1.add(a);
//        	}
//        	Gson gson1 = new Gson();
//        	ObservableList<Cardapio> data1 =
//        	        FXCollections.observableArrayList();
//        	al1.forEach(a -> {
//        		Cardapio c1 = gson1.fromJson(a.toString(), Cardapio.class);
//        		data1.add(c1);
//        	});
//        	this.tcNomeC.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Nome"));
//        	this.tcDescricaoC.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Descricao"));
//        	this.tcPrecoC.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Preco"));
//        	this.tbCardapioC.getItems().setAll(data1);
	}

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField tfItens;

    @FXML
    private Label lblBuscar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Cardapio> tbCardapio, tbCardapioC;
    
    @FXML
    private TableColumn<Cardapio,String> tcNome, tcNomeC;
    
    @FXML
    private TableColumn<Cardapio,String> tcPreco, tcPrecoC;
    
    @FXML
    private TableColumn<Cardapio,String> tcDescricao, tcDescricaoC;
    
    @FXML
    private TableColumn<Cardapio,String> tcQtd, tcQtdC;
    
    @FXML
    private Label lblQtd, lblHeader;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField tfBuscar;

    @FXML
    private TextField tfQtd;

    @FXML
    private Label lblItens;
    
    @FXML
    public void close() throws IOException {
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../view/Mesa_fechar_screen.fxml"));
    	Loader.load();
    	FechamentoController conta = Loader.getController();
    	conta.fill(Double.toString(this.total));
    	Parent root = Loader.getRoot();
    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();
		
		stage.setTitle("Conta");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    public void getText(int num) {
    	this.numero = num;
    	String banana = this.lblHeader.getText() + Integer.toString(num);
    	this.lblHeader.setText(banana);
    }

}

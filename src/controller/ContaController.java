package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import classs.Cardapio;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private String numero;
	private double total;
	public void initialize() throws IOException, ParseException, org.json.simple.parser.ParseException {
		this.refresh();
    	
    	ArrayList<Object> al1 = new ArrayList();
		URL url1 = new URL("https://us-central1-iinv-bar.cloudfunctions.net/cardapio/");
    	HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
    	connection1.setRequestMethod("GET");
    	connection1.setDoOutput(true);
    	connection1.setDoInput(true);
    	
    	BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(connection1.getInputStream()));
    	org.json.simple.parser.JSONParser parse1 = new org.json.simple.parser.JSONParser();
    	JSONArray obj1 = (JSONArray) parse1.parse(in1.readLine());
    	
    	
    	for(Object a : obj1) {
    		al1.add(a);
    	}
    	Gson gson1 = new Gson();
    	ObservableList<Cardapio> data1 =
    	        FXCollections.observableArrayList();
    	al1.forEach(a -> {
    		Cardapio c = gson1.fromJson(a.toString(), Cardapio.class);
    		data1.add(c);
    	});
    	this.tcNomeC.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Nome"));
    	this.tcPrecoC.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Preco"));
    	this.tbCardapio2.getItems().setAll(data1);
	    	

    	
    	FilteredList<Cardapio> filteredData = new FilteredList<>(data1, c -> true);
    	
    	tfBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(c -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (c.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches name
                } else {
                	return false; // Does not match.
                }
            });
        });
    	
    	SortedList<Cardapio> sortedData = new SortedList<>(filteredData);
    	
    	sortedData.comparatorProperty().bind(tbCardapio2.comparatorProperty());
    	
    	tbCardapio2.setItems(sortedData);
	}

    @FXML
    private Button btnAdicionar, btnAdicionar1;

    @FXML
    private Label lblBuscar;

    @FXML
    private Button btnFechar;

    @FXML
    private TableView<Cardapio> tbCardapio, tbCardapio2;
    
    @FXML
    private TableColumn<Cardapio,String> tcNome, tcNomeC;
    
    @FXML
    private TableColumn<Cardapio,String> tcPreco, tcPrecoC;
    
    @FXML
    private TableColumn<Cardapio,String> tcDescricao;
    
    @FXML
    private TableColumn<Cardapio,String> tcQtd, tcQtdC;
    
    @FXML
    private Label lblQtd, lblHeader;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TextField tfBuscar;

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
    	
    	Stage s = (Stage) btnFechar.getScene().getWindow();
	    s.close();
		
		Stage stage = new Stage();
		
		stage.setTitle("Conta");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    public int mano() {
    	int batata = Integer.parseInt(this.numero);
        return batata = batata -1;
    }
    
    public void refresh() throws IOException, org.json.simple.parser.ParseException {
    	this.total = 0;
    	ArrayList<Object> al = new ArrayList();
		System.out.println(Main.getNumMesa());
		int batata = Integer.parseInt(Main.getNumMesa());
        batata = batata -1;
		String banana = "https://us-central1-iinv-bar.cloudfunctions.net/mesa/itens/"+batata;
		URL url = new URL(banana);
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
    	this.tcPreco.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("preco"));
    	this.tcQtd.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("quantidade"));

    	this.tbCardapio.getItems().setAll(data); 
    	in.close();
    }
    
    @FXML
    public void memata() throws IOException, org.json.simple.parser.ParseException {
    	int index = tbCardapio2.getSelectionModel().getSelectedIndex();
    	Cardapio cardapio = tbCardapio2.getItems().get(index); 
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("id",cardapio.getId());
        jsonObject.put("nome",cardapio.getNome());
        jsonObject.put("descricao", cardapio.getDescricao());
        jsonObject.put("preco", cardapio.getPreco());
        jsonObject.put("quantidade", 1);
        int batata = Integer.parseInt(this.numero);
        batata = batata -1;
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/mesa/add/"+Integer.toString(batata);
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();
    	System.out.println(connection.getResponseCode());
    	
    	this.refresh();
    }
    
    @FXML
    public void rmv() throws IOException, org.json.simple.parser.ParseException {
    	int index = tbCardapio2.getSelectionModel().getSelectedIndex();
    	Cardapio cardapio = tbCardapio2.getItems().get(index); 
        
        int batata = Integer.parseInt(this.numero);
        batata = batata -1;
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/mesa/remove/"+Integer.toString(batata)+"/"+cardapio.getId();
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("DELETE");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write("");
    	wr.flush();
    	System.out.println(connection.getResponseCode());
    	
    	this.refresh();
    }
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    public void getText(int num) {
    	this.numero = Integer.toString(num);
    	String banana = this.lblHeader.getText() + Integer.toString(num);
    	this.lblHeader.setText(banana);
    }

}

package controller;
																																			
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Cardapio;
import classs.Cliente;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class CardapioController {
	ObservableList<Cardapio> data;

	

	@SuppressWarnings("unchecked")
	public void initialize () throws IOException, ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("https://us-central1-iinv-bar.cloudfunctions.net/cardapio/");
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
    	this.data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Cardapio c = gson.fromJson(a.toString(), Cardapio.class);
    		data.add(c);
    	});
    	this.tcId.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Id"));
    	this.tcId.setVisible(false); 
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Nome"));
    	this.tcDescricao.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Descricao"));
    	this.tcPreco.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Preco"));
    	this.tbCardapio.getItems().setAll(data);
	    	
   	
    	FilteredList<Cardapio> filteredData = new FilteredList<>(data, c -> true);
    	
    	tfFiltrar.textProperty().addListener((observable, oldValue, newValue) -> {
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
    	
    	sortedData.comparatorProperty().bind(tbCardapio.comparatorProperty());
    	
    	tbCardapio.setItems(sortedData);
tbCardapio.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	int index = tbCardapio.getSelectionModel().getSelectedIndex();
	Cardapio cardapio = tbCardapio.getItems().get(index); 
	this.tfNome.setText(cardapio.getNome());
	this.tfPreco.setText(Double.toString(cardapio.getPreco()));
	this.tfDesc.setText(cardapio.getDescricao());


});;
    	in.close();
	}
	
    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfFiltrar;

    @FXML
    private Label lblDesc;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private TableView<Cardapio> tbCardapio;
    @FXML
    private TableColumn<Cardapio,String> tcId;

    @FXML
    private TableColumn<Cardapio,String> tcNome;
    
    @FXML
    private TableColumn<Cardapio,String> tcPreco;
    
    @FXML
    private TableColumn<Cardapio,String> tcDescricao;
    
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
    public void voltar() throws Exception {
    	Main.sceneChange("sceneMenu");
    }
    
    @FXML
    public void addItem () throws IOException {
    	JSONObject jsonObject = new JSONObject();
    	Cardapio cardapio = new Cardapio();
    	cardapio.setPreco(Double.parseDouble(this.tfPreco.getText()));
    	cardapio.setNome(this.tfNome.getText());
    	cardapio.setDescricao(this.tfDesc.getText());
        
        jsonObject.put("nome",cardapio.getNome());
        jsonObject.put("descricao", cardapio.getDescricao());
        jsonObject.put("preco", cardapio.getPreco());
        
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/cardapio/create";
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
    		
    		//cardapio.setId(???);  // precisa do id no create
    		this.data.add(cardapio);
			alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("tops");
    		alert.setHeaderText("Deu ruim, talquei?");
    		alert.setContentText("mt problema mermao");

    		alert.showAndWait();
    	}

    }
    @FXML
    public void removeItem () throws IOException {
    	int index = tbCardapio.getSelectionModel().getSelectedIndex();
    	Cardapio cardapio = tbCardapio.getItems().get(index); 
        JSONObject jsonObject = new JSONObject();
        System.out.println(cardapio.getId());
        jsonObject.put("id", cardapio.getId());
    	
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/cardapio/remove";
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("DELETE");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();
    	
    	System.out.println(connection.getResponseMessage());
    	
    	this.data.remove(cardapio);

}
}

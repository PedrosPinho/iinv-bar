package controller;
																																			
import java.awt.List;																				
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CardapioController {

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
    	ObservableList<Cardapio> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Cardapio c = gson.fromJson(a.toString(), Cardapio.class);
    		data.add(c);
    	});
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Nome"));
    	this.tcDescricao.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Descricao"));
    	this.tcPreco.setCellValueFactory(new PropertyValueFactory<Cardapio, String>("Preco"));
    	this.tbCardapio.getItems().setAll(data);
	    	

    	
//    	this.tbCardapio = (TableView<Cardapio>) data;
    	
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
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("descricao", this.tfDesc.getText());
        jsonObject.put("preco", this.tfPreco.getText());
        
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
    		try {
				this.initialize();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

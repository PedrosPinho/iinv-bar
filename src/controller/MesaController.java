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
import classs.Funcionario;
import classs.Mesa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MesaController {

	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException, org.json.simple.parser.ParseException {
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
    	ObservableList<String> aa = FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Mesa c = gson.fromJson(a.toString(), Mesa.class);
    		data.add(c);
    		aa.add(Integer.toString(c.getNum_Mesa()));
    	});
    	this.selectMesa.setItems(aa);
//    	 cb.setItems(FXCollections.observableArrayList(values));
//         this.selectMesa.getSelectionModel().selectFirst();
    	

    	in.close();
		
	}

    @FXML
    private TableView<Mesa> tbMesas;
    

//    @FXML
//    private TableColumn<Mesa,String> tcMesa;
    

//    @FXML
//    private TableColumn<Mesa,String> tcPess;

    @FXML 
    private ChoiceBox<String> selectMesa;
    
    @FXML 
	private Button btnMesa;
    
    @FXML
    private ImageView btnVoltar;
    
    @FXML
    public void clicou() throws IOException {
    	Main.setNumMesa(selectMesa.getValue());
    	FXMLLoader Loader = new FXMLLoader();
    	Loader.setLocation(getClass().getResource("../view/Mesa_contas_screen.fxml"));
    	Loader.load();
    	ContaController conta = Loader.getController();
    	conta.getText(Integer.parseInt(selectMesa.getValue()));
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
    public void voltar() throws Exception {
    	Main.sceneChange("sceneMenu");
    }

}

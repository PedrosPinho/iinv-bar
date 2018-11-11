package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Cardapio;
import classs.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FuncionarioController {
	
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException {
		ArrayList<Object> al = new ArrayList();
		URL url = new URL("http://localhost:5000/iinv-bar/us-central1/users/funcionario");
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
    	ObservableList<Funcionario> data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Funcionario c = gson.fromJson(a.toString(), Funcionario.class);
    		data.add(c);
    	});
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));
    	this.tcFunc.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Funcao"));
    	this.tcCpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("CPF"));
    	this.tcIni.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Inicio"));
    	this.tcTel.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Telefone"));
    	this.tcEmail.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Email"));

    	
    	
    	this.tbFunc.getItems().setAll(data);


    	in.close();
	}

    @FXML
    private Button btnAdicionar;

    @FXML
    private TextField tfFiltrar;
    
    @FXML
    private TableView<Funcionario> tbFunc;
    
    @FXML
    private TableColumn<Funcionario,String> tcNome;
    
    @FXML
    private TableColumn<Funcionario,String> tcEmail;
    
    @FXML
    private TableColumn<Funcionario,String> tcTel;
    
    @FXML
    private TableColumn<Funcionario,String> tcCpf;
    
    @FXML
    private TableColumn<Funcionario,String> tcFunc;
    
    @FXML
    private TableColumn<Funcionario,String> tcIni;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnRelatorio;

    @FXML
    private ImageView btnVoltar;
    
    @FXML
    private Button btnRemover;
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }

}
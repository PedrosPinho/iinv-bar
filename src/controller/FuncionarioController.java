package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FuncionarioController {
	
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException {
		URL url = new URL("https://us-central1-iinv-bar.cloudfunctions.net/users/" +Main.getCpf());
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-type", "application/json, charset=utf-8");
        
    	BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection.getInputStream()));
    	org.json.simple.parser.JSONParser parse = new org.json.simple.parser.JSONParser();
    	JSONObject obj = (JSONObject) parse.parse(in.readLine());
    	
    	Gson gson = new Gson();
    	Funcionario funcionario = gson.fromJson(obj.toString(), Funcionario.class);
  	
abrirTelaCadastro(funcionario);
    	in.close();
	}
	private void abrirTelaCadastro(Funcionario funcionario) throws IOException {
		//Parent root = FXMLLoader.load(getClass().getResource("../view/Cliente_cadastro_screen.fxml"));
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			      "../view/Funcionario_cadastro_screen.fxml"
			    )
			  );


    		  
    	Scene scene = new Scene(loader.load());
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);			
		stage.setResizable(false);
		
		CadastroFuncController controller = 
    		    loader.getController();
    		  controller.carregaFuncionario(funcionario);

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show(); }

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
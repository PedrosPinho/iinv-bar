package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Cardapio;
import classs.Cliente;
import classs.Funcionario;
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

public class ClienteController {
	ObservableList<Cliente> data;
	@SuppressWarnings("unchecked")
	public void initialize() throws IOException, ParseException {
		listar();
	}

	private void listar() throws MalformedURLException, IOException, ProtocolException, ParseException {
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
    	this.data =
    	        FXCollections.observableArrayList();
    	al.forEach(a -> {
    		Cliente c = gson.fromJson(a.toString(), Cliente.class);
    		data.add(c);
    	});
    	this.tcNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Nome"));
    	this.tcCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
    	this.tcTel.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Telefone"));
    	this.tcEmail.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Email"));
    	this.tcFreq.setCellValueFactory(new PropertyValueFactory<Cliente, String>("Frequencia"));

    	
    	this.tbCliente.getItems().clear();
    	this.tbCliente.getItems().setAll(data);

    	FilteredList<Cliente> filteredData = new FilteredList<>(data, c -> true);
    	
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
    	
    	SortedList<Cliente> sortedData = new SortedList<>(filteredData);
    	
    	sortedData.comparatorProperty().bind(tbCliente.comparatorProperty());
    	
    	tbCliente.setItems(sortedData);
    	
    	in.close();
	}

    @FXML
    private Button btnAdicionar;

    @FXML
    private Label lblFiltrar;

    @FXML
    private TextField tfFiltrar;

    @FXML
    private TableView<Cliente> tbCliente;
    @FXML
    private TableColumn<Cliente,String> tcNome;
    
    @FXML
    private TableColumn<Cliente,String> tcEmail;
    
    @FXML
    private TableColumn<Cliente,String> tcTel;
    
    @FXML
    private TableColumn<Cliente,String> tcCpf;
    
    @FXML
    private TableColumn<Cliente,String> tcFreq;

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
    
    @FXML
    public void cadastro () throws IOException {
		abrirTelaCadastro(new Cliente());
   
    }

	private void abrirTelaCadastro(Cliente cliente) throws IOException {
		//Parent root = FXMLLoader.load(getClass().getResource("../view/Cliente_cadastro_screen.fxml"));
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			      "../view/Cliente_cadastro_screen.fxml"
			    )
			  );


    		  
    	Scene scene = new Scene(loader.load());
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);			
		stage.setResizable(false);
		
		CadastroCliController controller = 
    		    loader.getController();
    		  controller.carregaCliente(cliente);

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
    @FXML
    public void delete() throws IOException, ParseException {

    	int index = tbCliente.getSelectionModel().getSelectedIndex();
        Cliente cliente = tbCliente.getItems().get(index); 
        JSONObject jsonObject = new JSONObject();

jsonObject.put("id", cliente.getCpf());
    	
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/users/remove";
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
    	
    	this.data.remove(cliente);
    	//listar();
    }
    public void alterar() throws IOException {
    	int index = tbCliente.getSelectionModel().getSelectedIndex();
        Cliente cliente = tbCliente.getItems().get(index); 
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/users/"+ cliente.getCpf();
    	String data = getJSON(uri, 4000000);
    	Cliente clie = new Gson().fromJson(data, Cliente.class);
    	System.out.println(clie.getNome());
    	
    	abrirTelaCadastro(cliente);
    
    }
    
    public String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
           if (c != null) {
              try {
                  c.disconnect();
              } catch (Exception ex) {
                 Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
              }
           }
        }
        return null;
    }

}
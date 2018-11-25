package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import classs.Cliente;
import classs.Desconto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

public class FechamentoController {
	
//
//	@SuppressWarnings("unchecked")
//	public void initialize() {
//		tfDividir.textProperty().addListener(atualizarValorFinal());
//		tfTotal.textProperty().addListener(atualizarValorFinal());
//	}

//	private ChangeListener<? super String> atualizarValorFinal() {
//		return (observable, oldValue, newValue) -> {
//			try {
//				double dividir = Double.valueOf(tfDividir.getText().replace(',', '.'));
//				double total = Double.valueOf(tfTotal.getText().replace(',', '.'));			
//
//				double valorFinal = total/dividir;
//				tfFinal.setText(Double.toString(valorFinal).replace('.', ','));				
//			} catch (NumberFormatException e) {
//				// Ainda não tem um dos campos
//				tfFinal.setText("");				
//			}
//        };
//	}


    @FXML
    private Label lblFechamento;

    @FXML
    private Label lblCpf;

    @FXML
    private TextField tfTotal;

    @FXML
    private Button btnCadastrado;

    @FXML
    private TextField tfFinal;

    @FXML
    private ImageView btnVoltar;

    @FXML
    private Label lblMesa;

    @FXML
    private Label lblTotal;

    @FXML
    private TextField tfCpf;

    @FXML
    private Button btnPagar;

    @FXML
    private TextField tfDividir;

    @FXML
    private Button btnNCadastrado;
    
    public void fill(String val) {
    	this.tfTotal.setText(val); 
    }
    
    @FXML
    public void pagou() throws IOException {
    	JSONObject jsonObject = new JSONObject();
    	int batata = Integer.parseInt(Main.getNumMesa());
        batata = batata -1;
        jsonObject.put("cpf",Main.getCpf());
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/mesa/acabo/"+batata;
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
    		//AQUI MATEUSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("OK");
    		alert.setHeaderText("OK");
    		alert.setContentText("OK =)");

    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erro no login");
    		alert.setHeaderText("Erro tente novamente");
    		alert.setContentText("Algo deu errado =(");

    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    @FXML 
    public void cadastrado() throws IOException, ParseException {
    	JSONObject jsonObject = new JSONObject();
    	int batata = Integer.parseInt(Main.getNumMesa());
        batata = batata -1;
        jsonObject.put("cpf",this.tfCpf.getText());
    	String uri = "http://us-central1-iinv-bar.cloudfunctions.net/mesa/sell/"+batata;
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
    	if (connection.getResponseCode() == 200) {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("OK");
    		alert.setHeaderText("OK");
    		alert.setContentText("OK =)");
    		
    		ArrayList<Object> al = new ArrayList();
    		URL url1 = new URL("https://us-central1-iinv-bar.cloudfunctions.net/mesa/fidelidade");
        	HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
        	connection1.setRequestMethod("GET");
        	connection1.setDoOutput(true);
        	connection1.setDoInput(true);
        	BufferedReader in = new BufferedReader(
    		        new InputStreamReader(connection1.getInputStream()));
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
        	});
        	int frequ = data.get(0).getFrequencia();
        	int desc = data.get(0).getPorcentagem();
        	this.setDisc(frequ,desc);

    		alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Erro no login");
    		alert.setHeaderText("Erro tente novamente");
    		alert.setContentText("Algo deu errado =(");

    		alert.showAndWait();
    	}
    }
    
    public void setDisc(int f, int d) throws IOException, ParseException {
    	ArrayList<Object> al = new ArrayList();
    	System.out.println(f+d+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		URL url1 = new URL("https://us-central1-iinv-bar.cloudfunctions.net/user/"+this.tfCpf.getText());
    	HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
    	connection1.setRequestMethod("GET");
    	connection1.setDoOutput(true);
    	connection1.setDoInput(true);
    	System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbb");

    	BufferedReader in = new BufferedReader(
		        new InputStreamReader(connection1.getInputStream()));
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

    	if (data.get(0).getFrequencia() >= f) {
    		double odeioJava = Double.parseDouble(this.tfTotal.getText());
    		odeioJava = odeioJava - (odeioJava*d/100);
    		this.tfTotal.setText(Double.toString(odeioJava));
    	}
    }

    
}
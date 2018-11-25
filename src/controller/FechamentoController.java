package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class FechamentoController {
	

	@SuppressWarnings("unchecked")
	public void initialize() {
		tfDividir.textProperty().addListener(atualizarValorFinal());
		tfTotal.textProperty().addListener(atualizarValorFinal());
	}

	private ChangeListener<? super String> atualizarValorFinal() {
		return (observable, oldValue, newValue) -> {
			try {
				double dividir = Double.valueOf(tfDividir.getText().replace(',', '.'));
				double total = Double.valueOf(tfTotal.getText().replace(',', '.'));			

				double valorFinal = total/dividir;
				tfFinal.setText(Double.toString(valorFinal).replace('.', ','));				
			} catch (NumberFormatException e) {
				// Ainda não tem um dos campos
				tfFinal.setText("");				
			}
        };
	}


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
    private Label lblDividir;

    @FXML
    private TextField tfCpf;

    @FXML
    private Label lblFinal;

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
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    @FXML 
    public void cadastrado() throws IOException {
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
    	System.out.println(connection.getInputStream());
    	this.btnCadastrado.setDisable(true);
    	this.tfCpf.setDisable(true);
    	this.btnNCadastrado.setDisable(true);
    }

    
}
package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.jmgt.util.TextFieldFormatter;
import classs.Funcionario;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastroFuncController {
    @FXML
    private Button btnListar;

    @FXML
    private Label lblCpf;

    @FXML
    private Label lblFuncao;

    @FXML
    private TextField tfInicio;

    @FXML
    private Label lblEmail;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextField tfCpf;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblNome;

    @FXML
    private TextField tfNome;

    @FXML
    private Label lblInicio;

    @FXML
    private TextField tfFuncao;

	private String modo;
    
    @FXML
    public void cpfMask() throws IOException {
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###########");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(tfCpf);
    	tff.formatter();
    }
    
    @FXML
    public void voltar() throws IOException {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void list () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Funcionario_lista_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }

    @FXML
    public void create () throws IOException {
    	JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("nome", this.tfNome.getText());
        jsonObject.put("cpf", this.tfCpf.getText());
        jsonObject.put("email", this.tfEmail.getText());
        jsonObject.put("funcao", this.tfFuncao.getText());
        jsonObject.put("inicio", this.tfInicio.getText());
        jsonObject.put("type", "funcionario");
        
    	String uri = "https://us-central1-iinv-bar.cloudfunctions.net/users/"+modo;
    	URL url = new URL(uri);
    	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    	connection.setRequestMethod("POST");
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Content-Type", "application/json");
    	
    	OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
    	wr.write(jsonObject.toString());
    	wr.flush();
    	
    	if(connection.getResponseCode() == 200) {
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro tops");
			alert.setHeaderText("tops");
			alert.setContentText("tops");
	
			alert.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro nao tops");
			alert.setHeaderText("nao tops");
			alert.setContentText("Erro");
			alert.showAndWait();
    	}
    }

	public void carregaFuncionario(Funcionario funcionario) {
		if(funcionario.getCpf()== null) {
			this.modo = "create";
			tfCpf.setDisable(false);
		}else{
			this.modo = "alter";
		tfCpf.setText(funcionario.getCpf());
		tfFuncao.setText(funcionario.getFuncao());
		tfEmail.setText(funcionario.getEmail());
		tfNome.setText(funcionario.getNome());
		tfInicio.setText(funcionario.getInicio());

		
		tfCpf.setDisable(true);
		}
		
	}
}
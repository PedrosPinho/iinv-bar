package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import classs.Funcionario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuController {
    public void sair() throws IOException {
    	Stage stage = (Stage) lblSair.getScene().getWindow();
        stage.close();
    }
    
	
    @FXML
    private Button btnFidelidade;

    @FXML
    private Button btnMesa;

    @FXML
    private Button btnPerfil;

    @FXML
    private Label lblSair;

    @FXML
    private Button btnCliente;

    @FXML
    private Label time;
    
    @FXML
    private Label date;

    @FXML
    private Button btnCardapio;
    
    public void initialize() throws IOException, ParseException {
        initClock();
        initDate();
    }
    
    @FXML
    private void initDate() {

    	Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    @FXML
    private void initClock() {

    	Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    public void client () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Cliente_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void mesa () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Mesa_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void cardapio () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Cardapio_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
//    @FXML NAO SEI HEHEHE
//    public void logoff () throws IOException {
//		this.close();
//    }
    
    @FXML
    public void profile () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Funcionario_cadastro_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }
    
    @FXML
    public void fidelidade () throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../view/Fidelidade_screen.fxml"));

    	Scene scene = new Scene(root);
		
		Stage stage = new Stage();

		stage.setTitle("Menu");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
    }

}
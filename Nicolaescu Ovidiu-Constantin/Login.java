package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Login {
    @FXML
    private void initialize(){
        password.setFocusTraversable(false);
        loginBtn.setFocusTraversable(false);
        registerBtn.setFocusTraversable(false);
        username.setFocusTraversable(false);
    }
    public static String Username;
    @FXML
    private TextField username;
    @FXML
    private Button loginBtn;
    @FXML
    private PasswordField password;
    @FXML
    private Label messageError;
    @FXML
    private Button registerBtn;
    @FXML
    private void loginMethod() throws IOException {
        String user = username.getText();
        String pass = password.getText();
        Username = user;
        String passwordFromFile = "-1";
        File userFile = new File(".\\src\\sample\\users\\" + user + ".txt");
        if (userFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(userFile));
                passwordFromFile = reader.readLine();
                reader.close();
            if (pass.equals(passwordFromFile)) {
                Stage thisWindow = (Stage) registerBtn.getScene().getWindow();
                thisWindow.close();
                Stage primaryStage = new Stage();
                Parent rooting = FXMLLoader.load(getClass().getResource("meniu.fxml"));
                primaryStage.setTitle("Rent a car");
                primaryStage.setScene(new Scene(rooting));
                primaryStage.show();


            } else {
                setMessageError("Parola este invalida!\nReintrodu parola cu mai multa atentie.");
                password.clear();
            }
        }else{
            setMessageError("Utilizatorul nu exista!");
            username.clear();
            password.clear();
        }
    }
    @FXML
    private void registerMethod() throws IOException {
        Stage thisWindow = (Stage) registerBtn.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("registerUser.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }
    public void setMessageError(String str){
        messageError.setText(str);
    }
    @FXML
    Button exitBtn;
    @FXML
    public void exit(){
        Stage thisWindow = (Stage) exitBtn.getScene().getWindow();
        thisWindow.close();
    }
}


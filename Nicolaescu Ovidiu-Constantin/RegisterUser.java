package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterUser {
    @FXML
    private Button confirmRegister;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordCheck;
    @FXML
    private Label messageError;
    @FXML
    private void register() throws IOException {
        String user = username.getText();
        String pass = password.getText();
        String passCheck = passwordCheck.getText();
        File checkUser = new File(".\\src\\sample\\users\\" + user + ".txt");
        if(checkUser.exists()){
            messageError.setText("Numele de utilizator ales exista deja!\nTe rog alege alt username.");
            username.clear();
        }else{
            if(pass.equals(passCheck)) {

                checkUser.createNewFile();
                FileWriter writter = new FileWriter(checkUser);
                writter.write(pass);
                writter.write("\n-1");
                writter.close();
                Stage thisWindow = (Stage) confirmRegister.getScene().getWindow();
                thisWindow.close();
                Stage registerStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                registerStage.setScene(new Scene(root));
                registerStage.show();
            }
        }
    }
    @FXML
    private void cancelRegister() throws IOException {
        Stage thisWindow = (Stage) cancelButton.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}


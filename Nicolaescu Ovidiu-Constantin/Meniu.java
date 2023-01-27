package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Meniu {
    @FXML
    Button rent;
    @FXML
    Button logoutBtn;
    @FXML
    Button termsAndCondition;
    @FXML
    Button addVehicle;
    public void rent() throws IOException {
        Stage thisWindow = (Stage) rent.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("rentCar.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }

    @FXML
    public void back() throws IOException {
        Stage thisWindow = (Stage) logoutBtn.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }

    public void termsAndConditions() throws IOException {
        Stage thisWindow = (Stage) termsAndCondition.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("termeni.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();

    }

    public void initialize(){
        if(Login.Username.equals("Admin") || Login.Username.equals("admin")){
            addVehicle.setVisible(true);
        }else{
            addVehicle.setVisible(false);
        }

    }

    @FXML
    public void addVeh() throws IOException {
        Stage thisWindow = (Stage) addVehicle.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("addVehicle.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }

    @FXML
    public void promo() throws IOException {
        Stage thisWindow = (Stage) addVehicle.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("promo.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }
}


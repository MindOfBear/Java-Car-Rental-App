package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Termeni {
    @FXML
    Button back;
    @FXML
    public void back() throws IOException {
        Stage thisWindow = (Stage) back.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("meniu.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }
}


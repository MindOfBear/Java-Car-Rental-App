package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent rooting = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

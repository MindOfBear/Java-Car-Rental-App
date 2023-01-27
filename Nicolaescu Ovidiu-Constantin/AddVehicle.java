package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.format.TextStyle;
import java.util.ArrayList;

public class AddVehicle {
    ArrayList<Rent.vehicle> vehicles = new ArrayList<Rent.vehicle>();
    ObservableList<String> vehicles_to = FXCollections.observableArrayList();
    int countCars;
    @FXML
    ListView viewCars;

    public void initialize() throws IOException {

        String vehicles_file = ".\\src\\sample\\vehicles\\vehicles.txt";
        BufferedReader br = new BufferedReader(new FileReader(vehicles_file));
        String s = "";
        countCars = Integer.parseInt(br.readLine());
        while ((s = br.readLine()) != null) {
            String data[] = new String[8];
            data = s.split("/");
            System.out.println(data[0] + " 1 " + data[1] + " 2 " + data[2] + " 3 "+ data[3] + " 4 " + data[4] + " 5 " + data[5] + " 6 " + data[6] + " 7 " + data[7]);
            Rent.vehicle car = new Rent.vehicle(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
            vehicles.add(car);
        }
        for (int i = 0; i < countCars; i++) {
            vehicles_to.add(vehicles.get(i).printCarDetails());
        }
        viewCars.setItems(vehicles_to);

    }
    String nrCars = "";
    @FXML
    TextField imageName;
    @FXML
    TextField nameVeh;
    @FXML
    TextField colorVeh;
    @FXML
    TextField detVeh;
    @FXML
    TextField typeVeh;
    @FXML
    TextField priceVeh;
    @FXML
    Label errorMessage;
    @FXML
    public void addCar() throws IOException {
        countCars++;
        nrCars = nrCars + countCars;
        String name = nameVeh.getText() ;
        String color = colorVeh.getText();
        String det = detVeh.getText();
        String type = typeVeh.getText();
        String price = priceVeh.getText();
        String image = imageName.getText();
        if(!(name.isEmpty() && color.isEmpty() && det.isEmpty() && type.isEmpty() && price.isEmpty() && image.isEmpty())) {
            Rent.vehicle car = new Rent.vehicle(nrCars, name, color, type, det, "0", image, price);
            vehicles_to.add(car.printCarDetails());
            vehicles.add(car);
            File file = new File(".\\src\\sample\\vehicles\\vehicles.txt");
            FileWriter writer = new FileWriter(file, false);
            writer.write(countCars + "\n");
            for (int i = 0; i < countCars; i++) {
                writer.write(vehicles.get(i).printCarDetailsForData() + "\n");
            }
            writer.close();
            nameVeh.clear();
            colorVeh.clear();
            detVeh.clear();
            typeVeh.clear();
            priceVeh.clear();
            imageName.clear();
            errorMessage.setText("Masina a fost adaugata cu succes!");
        }else{
            errorMessage.setText("Toate campurile trebuie sa fie completate!");
        }
    }

    @FXML
    Button backBtn;
    @FXML
    public void back() throws IOException {
        Stage thisWindow = (Stage) backBtn.getScene().getWindow();
        thisWindow.close();
        Stage primaryStage = new Stage();
        Parent rooting = FXMLLoader.load(getClass().getResource("meniu.fxml"));
        primaryStage.setTitle("Rent a car");
        primaryStage.setScene(new Scene(rooting));
        primaryStage.show();
    }
}

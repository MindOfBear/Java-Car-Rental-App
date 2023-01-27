package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class Rent {
    @FXML
    Button forceReturn;
    @FXML
    Button returnCar;
    @FXML
    Button next;
    @FXML
    Button prev;
    @FXML
    Button rentACar;
    @FXML
    Label vehName;
    @FXML
    Label vehColor;
    @FXML
    Label type;
    @FXML
    Label description;
    @FXML
    ImageView vehImage;
    @FXML
    Label price;
    @FXML
    Label messageRent;
    @FXML
    Button backBtn;
    @FXML
    Label messageAdmin;

    int countCars;
    int actualCarId;
    static class vehicle{
        private String vehicleNumber, vehicleRent, imageName,vehRentPrice;
        private String vehicleName, vehicleColor, vehicleTip, vehicleDescription;
        public vehicle(String number, String name, String color, String tip, String description, String isRented, String imageName,String vehRentPrice){
            this.vehicleName = name;
            this.vehicleColor = color;
            this.vehicleTip = tip;
            this.vehicleDescription = description;
            this.vehicleNumber = number;
            this.vehicleRent = isRented;
            this.imageName = imageName;
            this.vehRentPrice = vehRentPrice;
        }
        //set-er
        public void setVehicleIsRented(String str){
            vehicleRent = str;
        }
        //get-er
        public String getVehName(){
            return vehicleName;
        }
        public String getIsVehRent() {return vehicleRent;}
        public String getVehColor(){
            return vehicleColor;
        }
        public String getVehTip(){
            return vehicleTip;
        }
        public String getVehDescription(){
            return vehicleDescription;
        }
        public String getVehicleNumber(){
            return vehicleNumber;
        }
        public String getVehicleImageName(){return imageName;}
        public String getVehRentPrice(){return vehRentPrice;}
        public String printCarDetails(){
            String str= getVehicleNumber() + " | " + getVehName() + " | " + getVehColor() + " | " + getVehTip() + " | " + getVehDescription() + " | " + getIsVehRent() + " | " + getVehRentPrice();
            return str;
        }
        public String printCarDetailsForData(){
            String str= getVehicleNumber() + "/" + getVehName() + "/" + getVehColor() + "/" + getVehTip() + "/" + getVehDescription() + "/" + getIsVehRent() + "/"+ getVehicleImageName() + "/" + getVehRentPrice();
            return str;
        }
    }
    //Functie care initializeaza array-ul de vehicule cand pagina se incarca
    ArrayList<vehicle>  vehicles = new ArrayList<vehicle>();
    public void initialize() throws IOException {
        String vehicles_file = ".\\src\\sample\\vehicles\\vehicles.txt";
        BufferedReader br = new BufferedReader(new FileReader(vehicles_file));
        String s=""; // salvez linia citita
        countCars = Integer.parseInt(br.readLine()); // salven nr masini
        while((s=br.readLine())!=null){ // verific sa nu fie nula
            String data[]=new String[8]; // creez un vector de stringuri
            data=s.split("/"); // setez un separator sa imparta toata linia citita in variabile gen ia caracter cu caracter pana intalneste '/'
            vehicle car = new vehicle(data[0],data[1],data[2],data[3],data[4],data[5],data[6], data[7]); //creez un  obiect de tipul vehicul si il initializez
            vehicles.add(car); // adaug obiectul
        }
        //incepe setarea obiectelor pe scena
        String imgName = vehicles.get(0).getVehicleImageName();
        File file = new File(".\\src\\sample\\vehicles\\images\\" + imgName + ".jpg");
        if(!file.exists()){
            file = new File(".\\src\\sample\\vehicles\\images\\stock.jpg");
        }
        String username = Login.Username;
        File user = new File(".\\src\\sample\\users\\" + username + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(user));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        Image image = new Image(file.toURI().toString());
        vehImage.setImage(image);
        vehName.setText("Marca: "+vehicles.get(0).getVehName());
        vehColor.setText("Culoare: "+vehicles.get(0).getVehColor());
        type.setText("Tip: "+vehicles.get(0).getVehTip());
        description.setText("Dotari: "+vehicles.get(0).getVehDescription());
        price.setText("Pret/zi: "+vehicles.get(0).getVehRentPrice()+" Lei");
        actualCarId = 0;
        forceReturn.setVisible(false);
        if(vehicleRented == actualCarId+1){
            rentACar.setVisible(false);
            returnCar.setVisible(true);
        }else{
            returnCar.setVisible(false);
            rentACar.setVisible(true);
        }
        String isRented = vehicles.get(actualCarId).getIsVehRent();
        if(!("0".equals(isRented))){
            if(username.equals("Admin")|| username.equals("admin")){
                messageAdmin.setText("Masina este inchiriata de: " + vehicles.get(0).getIsVehRent());
                forceReturn.setVisible(true);
            }else if(isRented.equals(username)){
                messageAdmin.setText("");
            }else{
                messageAdmin.setText("Masina este inchiriata, poti alege din flota noastra o masina disponibila.");
                rentACar.setDisable(true);
            }
        }else{
            rentACar.setDisable(false);
            messageAdmin.setText("");
            forceReturn.setVisible(false);
        }
        messageRent.setText("");
    }

    public void showVehicleNext() throws IOException {
        if(actualCarId < countCars-1) {
            actualCarId+=1;
        }else if(actualCarId == countCars-1){
            actualCarId = 0;
        }
        String imgName = vehicles.get(actualCarId).getVehicleImageName();
        File file = new File(".\\src\\sample\\vehicles\\images\\" + imgName + ".jpg");
        String username = Login.Username;
        if(!file.exists()){
            file = new File(".\\src\\sample\\vehicles\\images\\stock.jpg");
        }
        File user = new File(".\\src\\sample\\users\\" + username + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(user));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        Image image = new Image(file.toURI().toString());
        vehImage.setImage(image);
        vehName.setText("Marca: "+vehicles.get(actualCarId).getVehName());
        vehColor.setText("Culoare: "+vehicles.get(actualCarId).getVehColor());
        type.setText("Tip: "+vehicles.get(actualCarId).getVehTip());
        description.setText("Dotari: "+vehicles.get(actualCarId).getVehDescription());
        price.setText("Pret/zi: "+vehicles.get(actualCarId).getVehRentPrice()+" Lei");
        if(vehicleRented == actualCarId+1){
            rentACar.setVisible(false);
            returnCar.setVisible(true);
        }else{
            returnCar.setVisible(false);
            rentACar.setVisible(true);
        }
        String isRented = vehicles.get(actualCarId).getIsVehRent();
        if(!("0".equals(isRented))){
            if(username.equals("Admin")|| username.equals("admin")){
                messageAdmin.setText("Masina este inchiriata de: " + vehicles.get(actualCarId).getIsVehRent());
                forceReturn.setVisible(true);
            }else if(isRented.equals(username)){
                messageAdmin.setText("");
            }else{
                messageAdmin.setText("Masina este inchiriata, poti alege din flota noastra o masina disponibila.");
                rentACar.setDisable(true);
            }
        }else{
            rentACar.setDisable(false);
            messageAdmin.setText("");
            forceReturn.setVisible(false);
        }
        messageRent.setText("");
    }

    public void showVehiclePrev() throws IOException {
        if(actualCarId > 0) {
            actualCarId-=1;
        }else if(actualCarId == 0){
            actualCarId = countCars-1;
        }
        String imgName = vehicles.get(actualCarId).getVehicleImageName();
        File file = new File(".\\src\\sample\\vehicles\\images\\" + imgName + ".jpg");
        String username = Login.Username;
        if(!file.exists()){
            file = new File(".\\src\\sample\\vehicles\\images\\stock.jpg");
        }
        File user = new File(".\\src\\sample\\users\\" + username + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(user));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        Image image = new Image(file.toURI().toString());
        vehImage.setImage(image);
        vehName.setText("Marca: "+vehicles.get(actualCarId).getVehName());
        vehColor.setText("Culoare: "+vehicles.get(actualCarId).getVehColor());
        type.setText("Tip: "+vehicles.get(actualCarId).getVehTip());
        description.setText("Dotari: "+vehicles.get(actualCarId).getVehDescription());
        price.setText("Pret/zi: "+vehicles.get(actualCarId).getVehRentPrice() +" Lei");
        if(vehicleRented == actualCarId+1){
            rentACar.setVisible(false);
            returnCar.setVisible(true);//nu recunoaste daca masina nu e inchiriata testeaza!
        }else{
            returnCar.setVisible(false);
            rentACar.setVisible(true);
        }
        String isRented = vehicles.get(actualCarId).getIsVehRent();
        if(!("0".equals(isRented))){
            if(username.equals("Admin")|| username.equals("admin")){
                messageAdmin.setText("Masina este inchiriata de: " + vehicles.get(actualCarId).getIsVehRent());
                forceReturn.setVisible(true);
            }else if(isRented.equals(username)){
                messageAdmin.setText("");
            }else{
                messageAdmin.setText("Masina este inchiriata, poti alege din flota noastra o masina disponibila.");
                rentACar.setDisable(true);
            }
        }else{
            rentACar.setDisable(false);
            messageAdmin.setText("");
            forceReturn.setVisible(false);
        }
        messageRent.setText("");


    }
    @FXML
    public void rentThisCar() throws IOException, InterruptedException {
        String username = Login.Username;
        File file = new File(".\\src\\sample\\users\\" + username + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        if(vehicleRented == -1) {
            FileWriter writer = new FileWriter(file, false);
            writer.write(password+"\n");
            vehicleRented = actualCarId+1;
            String carNumber = "" + vehicleRented;
            writer.append(carNumber);
            messageRent.setText("Ai inchiriat cu succes un "+vehicles.get(actualCarId).getVehName()+"!");
            writer.close();
            returnCar.setVisible(true);
            rentACar.setVisible(false);
            vehicles.get(actualCarId).setVehicleIsRented(username);
            File file_cars = new File(".\\src\\sample\\vehicles\\vehicles.txt");
            FileWriter writer_cars = new FileWriter(file_cars, false);
            writer_cars.write(countCars + "\n");
            for (int i = 0; i < countCars; i++) {
                writer_cars.write(vehicles.get(i).printCarDetailsForData() + "\n");
            }
            writer_cars.close();
        }else{
            messageRent.setText("Ai deja inchiriat un vehicul, te rog returneazal pentru a inchiria altul!");
        }
    }
    @FXML
    public void returnThisCar() throws IOException, InterruptedException {
        String username = Login.Username;
        File file = new File(".\\src\\sample\\users\\" + username + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        if(vehicleRented !=-1) {
            FileWriter writer = new FileWriter(file, false);
            writer.write(password + "\n");
            vehicleRented = -1;
            String carNumber = "" + vehicleRented;
            writer.append(carNumber);
            messageRent.setText("Ai returnat cu succes vehiculul " + vehicles.get(actualCarId).getVehName() + "!");
            writer.close();
        }
        returnCar.setVisible(false);
        rentACar.setVisible(true);
        vehicles.get(actualCarId).setVehicleIsRented("0");
        File file_cars = new File(".\\src\\sample\\vehicles\\vehicles.txt");
        FileWriter writer_cars = new FileWriter(file_cars, false);
        writer_cars.write(countCars + "\n");
        for (int i = 0; i < countCars; i++) {
            writer_cars.write(vehicles.get(i).printCarDetailsForData() + "\n");
        }
        writer_cars.close();
    }

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

    @FXML
    public void returnareFortata() throws IOException {
        String userVeh = vehicles.get(actualCarId).getIsVehRent();
        File file_user = new File(".\\src\\sample\\users\\" + userVeh + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file_user));
        String password = buffer.readLine();
        int vehicleRented = Integer.parseInt(buffer.readLine());
        buffer.close();
        FileWriter writer = new FileWriter(file_user, false);
        writer.write(password + "\n");
        vehicleRented = -1;
        String carNumber = "" + vehicleRented;
        writer.append(carNumber);
        writer.close();
        vehicles.get(actualCarId).setVehicleIsRented("0");
        File file_cars = new File(".\\src\\sample\\vehicles\\vehicles.txt");
        FileWriter writer_cars = new FileWriter(file_cars, false);
        writer_cars.write(countCars + "\n");
        for (int i = 0; i < countCars; i++) {
            writer_cars.write(vehicles.get(i).printCarDetailsForData() + "\n");
        }
        writer_cars.close();
    }

    public static void main(String[] args) {

    }
}


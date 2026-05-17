import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VehicleEntryController {

    ParkingRecordService parkingRecordService = new ParkingRecordService();
    VehicleService vehicleService = new VehicleService();

    @FXML private TextField licensePlateField;
    @FXML private Label messageLabel;
    @FXML private VBox vehicleFormBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private TextField brandField;
    @FXML private TextField modelField;
    @FXML private TextField colorField;

    @FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Car", "Truck", "Minibus", "Motorcycle");
    }

    @FXML
    private void searchVehicle() {

        String licensePlate = licensePlateField.getText();

        if (!vehicleService.isValidLicensePlate(licensePlate)) {
            messageLabel.setText("License plate cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        boolean exists = vehicleService.isVehicleExists(licensePlate);

        if (exists) {
            parkingRecordService.createEntryRecord(licensePlate);
            messageLabel.setText("Vehicle found. Parking record created.");
            messageLabel.setStyle("-fx-text-fill: green;");
        } else {
            messageLabel.setText("Vehicle not found. Please enter vehicle details.");
            messageLabel.setStyle("-fx-text-fill: red;");
            vehicleFormBox.setVisible(true);
            vehicleFormBox.setManaged(true);
        }
    }

    @FXML
    private void saveVehicle() {

        String licensePlate = licensePlateField.getText();
        String type = typeComboBox.getValue();
        String brand = brandField.getText();
        String model = modelField.getText();
        String color = colorField.getText();

        if (!vehicleService.areVehicleFieldsValid(licensePlate, type, brand, model, color)) {
            messageLabel.setText("Please fill all fields.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Vehicle vehicle = new Vehicle(licensePlate, type, brand, model, color);

        boolean saved = vehicleService.registerVehicle(vehicle);

        if(saved){
            parkingRecordService.createEntryRecord(licensePlate);
            messageLabel.setText("Vehicle saved successfully.");
            messageLabel.setStyle("-fx-text-fill: green;");
        }else{
            messageLabel.setText("Vehicle could not be saved.");
        }
    }
}
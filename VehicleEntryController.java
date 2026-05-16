import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VehicleEntryController {

    ParkingRecordDatabase parkingRecordDatabase = new ParkingRecordDatabase();
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
            Vehicle vehicle = vehicleService.vehicleDatabase.getVehicle(licensePlate);
            parkingRecordDatabase.saveEntryRecord(vehicle.getVehicleId());
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
        // veritabanı bağlantısı yapılınca burası dolacak
    }
}
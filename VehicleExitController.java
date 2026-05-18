import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class VehicleExitController {

    ParkingRecordService parkingRecordService = new ParkingRecordService();
    VehicleService vehicleService = new VehicleService();

    @FXML private TextField licensePlateField;
    @FXML private Label messageLabel;
    @FXML private VBox infoBox;
    @FXML private Label entryTimeLabel;
    @FXML private Label exitTimeLabel;
    @FXML private Label durationLabel;
    @FXML private Label feeLabel;

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

            double fee = parkingRecordService.calculateFee(licensePlate);
            
            Vehicle vehicle = vehicleService.vehicleDatabase.getVehicle(licensePlate);
            ParkingRecord record = parkingRecordService.parkingRecordDatabase.getActiveRecord(vehicle.getVehicleId());

            entryTimeLabel.setText("Entry Time: " + record.getEntryTime().toString());
            exitTimeLabel.setText("Exit Time: " + LocalDateTime.now().toString());
            
            long minutes = ChronoUnit.MINUTES.between(record.getEntryTime(), LocalDateTime.now());
            durationLabel.setText("Duration: " + minutes + " minutes");
            feeLabel.setText("Fee: " + String.format("%.2f", fee) + " TL");

            infoBox.setVisible(true);
            infoBox.setManaged(true);

            messageLabel.setText("");
        } else {
            messageLabel.setText("Vehicle not found.");
            messageLabel.setStyle("-fx-text-fill: red;");
        }  
    }

    @FXML
    private void completeExit() {
        String licensePlate = licensePlateField.getText();
        parkingRecordService.createExitRecord(licensePlate);
        messageLabel.setText("Exit completed successfully.");
        messageLabel.setStyle("-fx-text-fill: green;");
        infoBox.setVisible(false);
        infoBox.setManaged(false);
    }
}

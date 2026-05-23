import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class ParkedVehiclesController {

    ParkingRecordService parkingRecordService = new ParkingRecordService();
    VehicleService vehicleService = new VehicleService();

    @FXML private TextField licensePlateField;
    @FXML private Label messageLabel;
    @FXML private TableView<String[]> recordTable;
    @FXML private TableColumn<String[], String> entryTimeColumn;
    @FXML private TableColumn<String[], String> exitTimeColumn;
    @FXML private TableColumn<String[], String> feeColumn;

    @FXML
    public void initialize() {
        entryTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        exitTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        feeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));
    }

    @FXML
    private void searchVehicle() {

        String licensePlate = licensePlateField.getText();

        if (!vehicleService.isValidLicensePlate(licensePlate)) {
            messageLabel.setText("License plate cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        int vehicleId = vehicleService.getVehicleId(licensePlate);

        if (vehicleId == -1) {
            messageLabel.setText("Vehicle not found.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        List<String[]> list = parkingRecordService.getVehicleRecords(vehicleId);

        if (list.isEmpty()) {
            messageLabel.setText("No records found.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        ObservableList<String[]> observableList = FXCollections.observableArrayList(list);
        recordTable.setItems(observableList);

    }
}
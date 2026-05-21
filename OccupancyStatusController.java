import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class OccupancyStatusController {

    ParkingRecordService parkingRecordService = new ParkingRecordService();

    @FXML private Label capacityLabel;
    @FXML private TableView<String[]> vehicleTable;
    @FXML private TableColumn<String[], String> licensePlateColumn;
    @FXML private TableColumn<String[], String> entryTimeColumn;

    @FXML
    public void initialize() {
        licensePlateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        entryTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));

        List<String[]> list = parkingRecordService.getActiveVehicles();
        ObservableList<String[]> observableList = FXCollections.observableArrayList(list);
        vehicleTable.setItems(observableList);

        capacityLabel.setText("Vehicles inside: " + list.size());
    }
}
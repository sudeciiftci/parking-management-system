import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.List;

public class DailyRevenueController {

    ParkingRecordService parkingRecordService = new ParkingRecordService();

    @FXML private DatePicker datePicker;
    @FXML private Label messageLabel;
    @FXML private Label totalRevenueLabel;
    @FXML private TableView<String[]> revenueTable;
    @FXML private TableColumn<String[], String> licensePlateColumn;
    @FXML private TableColumn<String[], String> entryTimeColumn;
    @FXML private TableColumn<String[], String> exitTimeColumn;
    @FXML private TableColumn<String[], String> feeColumn;

    @FXML
    public void initialize() {
        licensePlateColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        entryTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        exitTimeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));
        feeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[3]));
    }

    @FXML
    private void searchRevenue() {

        LocalDate date = datePicker.getValue();

        if (date == null) {
            messageLabel.setText("Please select a date.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        List<String[]> list = parkingRecordService.getDailyRevenue(date.toString());

        if (list.isEmpty()) {
            messageLabel.setText("No records found for this date.");
            messageLabel.setStyle("-fx-text-fill: red;");
            totalRevenueLabel.setText("");
            return;
        }

        ObservableList<String[]> observableList = FXCollections.observableArrayList(list);
        revenueTable.setItems(observableList);

        double total = list.stream()
                .mapToDouble(row -> row[3] != null ? Double.parseDouble(row[3]) : 0)
                .sum();

        totalRevenueLabel.setText("Total Revenue: " + String.format("%.2f", total) + " TL");
        messageLabel.setText("");
    }
}
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private void openVehicleEntry() throws Exception {
        openWindow("vehicleEntry.fxml", "Vehicle Entry");
    }

    @FXML
    private void openVehicleExit() throws Exception {
        openWindow("vehicleExit.fxml", "Vehicle Exit");
    }

    @FXML
    private void openOccupancyStatus() throws Exception {
        openWindow("occupancyStatus.fxml", "Occupancy Status");
    }

    @FXML
    private void openParkedVehicles() throws Exception {
        openWindow("parkedVehicles.fxml", "Parked Vehicles");
    }

    @FXML
    private void openDailyRevenue() throws Exception {
        openWindow("dailyRevenue.fxml", "Daily Revenue");
    }

    private void openWindow(String fxmlFile, String title) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
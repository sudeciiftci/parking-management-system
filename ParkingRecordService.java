import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ParkingRecordService {

    VehicleService vehicleService = new VehicleService();
    ParkingRecordDatabase parkingRecordDatabase = new ParkingRecordDatabase();

    public boolean createEntryRecord(String licensePlate) {
        Vehicle vehicle = vehicleService.vehicleDatabase.getVehicle(licensePlate);
        if (vehicle == null) {
            return false;
        }
        parkingRecordDatabase.saveEntryRecord(vehicle.getVehicleId());
        return true;
    }

    public double calculateFee(String licensePlate){
        Vehicle vehicle = vehicleService.vehicleDatabase.getVehicle(licensePlate);
        ParkingRecord parkingRecord = parkingRecordDatabase.getActiveRecord(vehicle.getVehicleId());

        String type = vehicle.getType();
        long minutes = ChronoUnit.MINUTES.between(parkingRecord.getEntryTime(), LocalDateTime.now());
        double hours = minutes / 60.0;

        if(type.equals("Car")){
            return hours * 50;
        }
        if(type.equals("Truck")){
            return hours * 100;
        }
        if(type.equals("Minibus")){
            return hours * 80;
        }
        if(type.equals("Motorcycle")){
            return hours * 30;
        }

        return 0;
    }

    public boolean createExitRecord(String licensePlate) {
        Vehicle vehicle = vehicleService.vehicleDatabase.getVehicle(licensePlate);
        if (vehicle == null) {
            return false;
        }
        double fee = calculateFee(licensePlate);
        parkingRecordDatabase.saveExitRecord(vehicle.getVehicleId(), fee);
        return true;
    }

    public List<String[]> getActiveVehicles() {
        return parkingRecordDatabase.getActiveVehicles();
    }


}
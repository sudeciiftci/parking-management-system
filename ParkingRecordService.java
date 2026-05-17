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
}
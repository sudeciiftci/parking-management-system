public class VehicleService {

    VehicleDatabase vehicleDatabase = new VehicleDatabase();

    public boolean isValidLicensePlate(String licensePlate) {
        if (licensePlate == null || licensePlate.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isVehicleExists(String licensePlate) {
        if (!isValidLicensePlate(licensePlate)) {
            return false;
        }
        Vehicle vehicle = vehicleDatabase.getVehicle(licensePlate);
        return vehicle != null;
    }
}
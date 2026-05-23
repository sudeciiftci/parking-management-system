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

    public boolean areVehicleFieldsValid(String licensePlate, String type, String brand, String model, String color) {
        return !(licensePlate.isBlank() ||
                type == null || type.isBlank() ||
                brand.isBlank() ||
                model.isBlank() ||
                color.isBlank());
    }
    
    public boolean registerVehicle(Vehicle vehicle) {
        return vehicleDatabase.saveVehicle(vehicle);
    }

    public int getVehicleId(String licensePlate) {
        Vehicle vehicle = vehicleDatabase.getVehicle(licensePlate);
        if (vehicle == null) {
            return -1;
        }
        return vehicle.getVehicleId();
    }
}
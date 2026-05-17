import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDatabase {

    Database database = new Database();

    public Vehicle getVehicle(String licensePlate) {

        String sql = "SELECT * FROM vehicles WHERE license_plate = ?";

        try {
            PreparedStatement ps = database.con().prepareStatement(sql);
            ps.setString(1, licensePlate);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("license_plate"),
                    rs.getString("type"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("color")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveVehicle(Vehicle vehicle){

        String sql = "INSERT INTO vehicles (license_plate, type, brand, model, color) VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement ps = database.con().prepareStatement(sql)){

            ps.setString(1, vehicle.getLicensePlate());
            ps.setString(2, vehicle.getType());
            ps.setString(3, vehicle.getBrand());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getColor());

            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}
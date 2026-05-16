import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
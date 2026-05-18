import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingRecordDatabase {

    Database database = new Database();
    VehicleDatabase vehicleDatabase = new VehicleDatabase();

    public void saveEntryRecord(int vehicleId){

        String sql = "INSERT INTO parking_records (vehicle_id, entry_time) VALUES (?, NOW())";

        try(PreparedStatement ps = database.con().prepareStatement(sql)){

            ps.setInt(1, vehicleId);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ParkingRecord getActiveRecord(int vehicleId) {

        String sql = "SELECT * FROM parking_records WHERE vehicle_id = ? AND exit_time IS NULL";

        try {
            PreparedStatement ps = database.con().prepareStatement(sql);
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new ParkingRecord(
                    rs.getInt("record_id"),
                    rs.getInt("vehicle_id"),
                    rs.getTimestamp("entry_time").toLocalDateTime(),
                    null
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveExitRecord(int vehicleId){

        String sql = "UPDATE parking_records SET exit_time = NOW() WHERE vehicle_id = ? AND exit_time IS NULL";

        try(PreparedStatement ps = database.con().prepareStatement(sql)){

            ps.setInt(1, vehicleId);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}

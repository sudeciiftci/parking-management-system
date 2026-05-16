import java.sql.PreparedStatement;
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
    
}

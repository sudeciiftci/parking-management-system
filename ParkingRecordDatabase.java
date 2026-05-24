import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void saveExitRecord(int vehicleId, double fee){

        String sql = "UPDATE parking_records SET exit_time = NOW(), fee = ?  WHERE vehicle_id = ? AND exit_time IS NULL";

        try(PreparedStatement ps = database.con().prepareStatement(sql)){

            ps.setInt(2, vehicleId);
            ps.setDouble(1, fee);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<String[]> getActiveVehicles() {

        List<String[]> list = new ArrayList<>();
        String sql = "SELECT v.license_plate, pr.entry_time FROM parking_records pr JOIN vehicles v ON pr.vehicle_id = v.vehicle_id WHERE pr.exit_time IS NULL";

        try {
            PreparedStatement ps = database.con().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = new String[2];
                row[0] = rs.getString("license_plate");
                row[1] = rs.getString("entry_time");  
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getVehicleRecords(int vehicleId) {

        List<String[]> list = new ArrayList<>();
        String sql = "SELECT pr.entry_time, pr.exit_time, pr.fee FROM parking_records pr WHERE pr.vehicle_id = ?";

        try {
            PreparedStatement ps = database.con().prepareStatement(sql);
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = new String[3];
                row[0] = rs.getString("entry_time");
                row[1] = rs.getString("exit_time");
                row[2] = rs.getString("fee");
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String[]> getDailyRevenue(String date){
        List<String[]> list = new ArrayList<>();

        String sql = "SELECT v.license_plate, pr.entry_time, pr.exit_time, pr.fee FROM parking_records pr JOIN vehicles v ON pr.vehicle_id = v.vehicle_id WHERE DATE(pr.exit_time) = ?";

        try(
            PreparedStatement ps = database.con().prepareStatement(sql)
        ){

            ps.setString(1, date);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String[] row = new String[4];

                row[0] = rs.getString("license_plate");
                row[1] = rs.getString("entry_time");
                row[2] = rs.getString("exit_time");
                row[3] = rs.getString("fee");

                list.add(row);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }
}

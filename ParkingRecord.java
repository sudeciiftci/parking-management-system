import java.time.LocalDateTime;

public class ParkingRecord {
    private int recordId;
    private int vehicleId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public ParkingRecord(int recordId, int vehicleId, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.recordId = recordId;
        this.vehicleId = vehicleId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
}
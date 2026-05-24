# Parking Management System

A desktop application for managing a parking lot. Built with Java and JavaFX, using MySQL for data storage.

## Features

- **Vehicle Entry** — Register a vehicle and create a parking record. If the vehicle isn't in the system, it can be added on the spot.
- **Vehicle Exit** — Look up a vehicle by license plate, calculate the fee, and complete the exit.
- **Occupancy Status** — See all vehicles currently parked.
- **Parked Vehicles** — Search parking history by license plate.
- **Daily Revenue** — View all completed records and total revenue for a selected date.

## Pricing

| Vehicle Type | Rate          |
| Car          | 50 TL / hour  |
| Minibus      | 80 TL / hour  |
| Truck        | 100 TL / hour |
| Motorcycle   | 30 TL / hour  |

## Tech Stack

- Java 25
- JavaFX 26
- MySQL
- JDBC (MySQL Connector/J)

## Setup

1. Clone the repository.
2. Create a MySQL database named `parking_management` and run the SQL below.
3. Create a `DBConfig.java` file (excluded from git):

```java
public class DBConfig {
    public static final String URL = "jdbc:mysql://localhost:3306/parking_management";
    public static final String USER = "your_username";
    public static final String PASSWORD = "your_password";
}
```

4. Add JavaFX SDK and MySQL Connector JAR to the classpath.
5. Run `Main.java`.

## Database

```sql
CREATE DATABASE parking_management;
USE parking_management;

CREATE TABLE vehicles (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    type VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL
);

CREATE TABLE parking_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    entry_time DATETIME NOT NULL,
    exit_time DATETIME,
    fee DOUBLE,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(vehicle_id)
);
```

## Architecture

The project follows a 3-layer architecture:

- **Model** — `Vehicle`, `ParkingRecord`
- **Service** — `VehicleService`, `ParkingRecordService`
- **Database** — `VehicleDatabase`, `ParkingRecordDatabase`

package uz.project.service;
import static uz.project.dataBase.DataBase.*;

import uz.project.fileWorker.ReadWriteFile;
import uz.project.model.Vehicle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VehicleService extends ReadWriteFile {
    public void addVehicle(Vehicle vehicle) throws IOException {
        ArrayList<Vehicle> vehicleArrayList = readVehicle();
        vehicle.setEnteredTime(LocalDateTime.now());
        vehicleArrayList.add(vehicle);
        writeVehicle(vehicleArrayList);
    }
    public void update(Vehicle vehicle) throws IOException {
        ArrayList<Vehicle> vehicleArrayList = readVehicle();
        int i =0;
        for (Vehicle ve : vehicleArrayList) {
            if (ve.getLicenceNumber().equals(vehicle.getLicenceNumber())){
                vehicleArrayList.set(i,vehicle);
                writeVehicle(vehicleArrayList);
                break;
            }
            i++;
        }
    }

}

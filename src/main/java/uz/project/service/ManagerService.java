package uz.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.project.entity.Admin;
import uz.project.entity.History;
import uz.project.fileWorker.ReadWriteFile;
import uz.project.model.Parking;
import uz.project.model.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static uz.project.dataBase.DataBase.*;

public class ManagerService extends ReadWriteFile {
    public void createAdmin(Admin admin) throws IOException {
        admin.setAdminId(UUID.randomUUID());
        writeAdmins(admin);
    }
    public boolean login(){
        int counter = 3;
        while (counter != 0) {
            System.out.print("Enter login : ");
            login = scannerStr.nextLine();
            System.out.print("Enter password : ");
            password = scannerStr.nextLine();
            if (manager.getLogin().equals(login) && manager.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println(" login : " + login + " || password : " + password + " is wrong " +
                        "\n\n you have : " + --counter + " chance");
            }
        }
        return false;
    }
    public void histories(){
        String historyMenu = """
                1. Cars in Parking
                2. Cars history
                0. back
                """;
        while (true){
            System.out.println(historyMenu);
            try {
                inputInt = scanner.nextInt();
                switch (inputInt){
                    case 1-> carsInParking();
                    case 2-> carsHistory();
                    case 0->{
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
            }
        }
    }
    public void carsInParking() throws IOException {
        ArrayList<Parking> parkings = readParking();
        while (true) {
            int i = 1;
            for (Parking parking : parkings) {
                System.out.println(i++ + ". name : " + parking.getName() + " total space : " + parking.getAllSlots() + "\n");
            }
            System.out.print("Choose parking :");
            try {
                inputInt = scanner.nextInt();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (inputInt>0 && inputInt<=parkings.size()){
            Parking parking = parkings.get(inputInt - 1);
            ArrayList<Vehicle> vehicleArrayList = readVehicle();
            if (vehicleArrayList.size()>0) {
                    int i = 1;
                    boolean isThereCar = false;
                    for (Vehicle vehicle:vehicleArrayList) {
                        if (vehicle.getParkingId().equals(parking.getParkingId()) && vehicle.isInPark()){
                            System.out.println(i++ +". Car number: "+vehicle.getLicenceNumber()+" || Car type : "+vehicle.getVehicleType()
                                    +" || State : "+vehicle.getState()+"\n");
                            isThereCar = true;
                        }
                    }
                    if (!isThereCar){
                        System.out.println("There is not cars \n");
                    }

            }else {
                System.out.println("There is not cars yet");
            }
        }
    }
    public void carsHistory() throws IOException {
        ArrayList<Parking> parkings = readParking();
        while (true) {
            int i = 1;
            for (Parking parking : parkings) {
                System.out.println(i++ + ". name : " + parking.getName() + " total space" + parking.getAllSlots() + "\n");
            }
            System.out.print("Choose parking :");
            try {
                inputInt = scanner.nextInt();
                break;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ArrayList<History> historyArrayList = readHistory();
        if (historyArrayList.size()>0){
            int i = 1;
            for (History history:historyArrayList) {
                if (history.getParkingId().equals(parkings.get(inputInt-1).getParkingId())){
                    System.out.println(i++ +". Car number : "+history.getCarNumber()+" entered time : "+history.getStarTime()+
                            " left time : "+history.getEndTime()+" Total time : "+history.getDurationTime()+
                            " Price : "+history.getAmount());
                }
            }
        }else {
            System.out.println("No history");
        }
    }
}

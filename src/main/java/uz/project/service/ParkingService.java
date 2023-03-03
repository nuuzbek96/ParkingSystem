package uz.project.service;

import uz.project.entity.Admin;
import uz.project.exeption.DataNotFoundException;
import uz.project.exeption.NotAvailableSpaceException;
import uz.project.fileWorker.ReadWriteFile;
import uz.project.model.Parking;
import uz.project.model.Vehicle;
import uz.project.validation.AdminValidation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import static uz.project.dataBase.DataBase.*;
import static uz.project.dataBase.DataBase.scanner;

public class ParkingService extends ReadWriteFile {
    AdminValidation adminValidation = new AdminValidation();
    ManagerService managerService = new ManagerService();
    public void createParking() throws IOException {
        while (true) {
            boolean founded = false;
            System.out.print("Create name for parking :");
            name = scannerStr.nextLine();
            for (Parking parking : readParking()) {
                if (parking.getName().equals(name)){
                    founded = true;
                }
            }
            if (!founded){
                break;
            }else {
                System.out.println("There is parking with this name");
            }
        }
        while (true){
            System.out.print("Enter number of floors (0.back) : ");
            try {
                floor = scanner.nextInt();
                if (floor == 0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true){
            System.out.print("Enter number of rows (0.back) :");
            try {
                row = scanner.nextInt();
                if (row == 0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true){
            System.out.print("Enter numbers of columns (0.back): ");
            try {
                columns = scanner.nextInt();
                if (columns == 0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.print("Price for Trucks (0.back): ");
            try {
                priceForTruck = scanner.nextDouble();
                if (priceForTruck <= 0.0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.print("Price for Sedans (0.back): ");
            try {
                priceForSedans = scanner.nextDouble();
                if (priceForTruck <= 0.0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }while (true) {
            System.out.print("Price for motor bikes (0.back): ");
            try {
                priceForMotorBikes = scanner.nextDouble();
                if (priceForTruck <= 0.0){
                    return;
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true) {
            System.out.println("Enter name of admin : ");
            String nameOfAdmin = scannerStr.nextLine();
            System.out.println("Enter login of admin : ");
            String adminLogin = scannerStr.nextLine();
            System.out.println("Enter password of admin :");
            String password = scannerStr.nextLine();
            try {
                    adminValidation.isCurrentAdmin(adminLogin,password);
                    System.out.println("\nThere is admin with this data\n");
            } catch (Exception e) {
                Admin admin = new Admin(adminLogin,password,nameOfAdmin);
                Parking parking = new Parking(floor,row,columns,priceForMotorBikes,priceForTruck,priceForSedans,admin,name);
                addParking(parking);
                break;
            }
        }
    }
    public void addParking(Parking parking) throws IOException {
        parking.setParkingId(UUID.randomUUID());
        parking.setAllSlots((parking.getRows()*parking.getColumns())*parking.getFloors());
        parking.getAdmin().setParkingId(parking.getParkingId());
        try {
            managerService.createAdmin(parking.getAdmin());
            try {
                writeParking(parking);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Parking getParkingWithId(UUID parkingId) throws FileNotFoundException {
        for (Parking p:readParking()) {
            if (p.getParkingId().equals(parkingId)){
                return p;
            }
        }
        throw new DataNotFoundException("There is not paring with this id");
    }
    public ArrayList<Integer>freePlaces(int floor,Parking parking) throws IOException {
        int sizeOfFloor = parking.getColumns()*parking.getRows();
        int from = sizeOfFloor*(floor-1);
        int upTo = sizeOfFloor*floor;
        ArrayList<Integer>freePlace = new ArrayList<>();
            ArrayList<Vehicle> vehicleArrayList = readVehicle();
        for (int i = from; i <upTo; i++) {
            boolean isFount = false;
            for (Vehicle v:vehicleArrayList) {
                if (v.getParkingId().equals(parking.getParkingId()) && v.getState()==i){
                    isFount = true;
                    break;
                }
            }
            if (!isFount){
                freePlace.add(i);
            }
        }
        if (freePlace.size()>0){
            return freePlace;
        }
        throw new NotAvailableSpaceException("No free space");
    }
}

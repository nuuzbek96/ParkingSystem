package uz.project.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uz.project.entity.Admin;
import uz.project.entity.History;
import uz.project.enums.VehicleType;
import uz.project.exeption.DataNotFoundException;
import uz.project.fileWorker.ReadWriteFile;
import uz.project.model.Parking;
import uz.project.model.Vehicle;
import uz.project.validation.AdminValidation;
import uz.project.validation.LicenceNumberValidation;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.UUID;

import static uz.project.dataBase.DataBase.*;

public class AdminService extends ReadWriteFile {
    AdminValidation adminValidation = new AdminValidation();
    VehicleService vehicleService = new VehicleService();
    LicenceNumberValidation licenceNumberValidation = new LicenceNumberValidation();
    ParkingService parkingService = new ParkingService();

    public void login() {
        int counter = 3;
        while (counter != 0) {
            System.out.print("Enter login : ");
            login = scannerStr.nextLine();
            System.out.print("Enter password : ");
            password = scannerStr.nextLine();
            try {
                Admin currentAdmin = adminValidation.isCurrentAdmin(login, password);
                menu(currentAdmin.getParkingId());
                break;
            } catch (DataNotFoundException e) {
                System.out.println(e.getMessage() + " login : " + login + " || password : " + password +
                        "\n\n you have : " + --counter + " chance");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void menu(UUID parkingId) {
        String menu = """
                1. Enter car
                2. Exit car
                3. Cars in parking
                0. Back main menu
                """;
        while (true) {
            System.out.println(menu);
            try {
                inputInt = scanner.nextInt();
                switch (inputInt) {
                    case 1 -> {
                        enterCar(parkingId);
                    }
                    case 2 -> {
                        exitCar(parkingId);
                    }
                    case 3 -> {
                        freePlace(parkingId);
                    }
                    case 0 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }

    public void exitCar(UUID parkingId) throws IOException {
        while (true) {
            Parking parkingWithId = parkingService.getParkingWithId(parkingId);
            ArrayList<Vehicle> vehicleArrayList = freePlace(parkingId);
            System.out.print("Choose car : ");
            try {
                int nextInt = scanner.nextInt();
                if (nextInt == 0) {
                    return;
                } else if (nextInt > 0 && nextInt <= vehicleArrayList.size()) {
                    Vehicle vehicle = vehicleArrayList.get(nextInt - 1);
                    LocalDateTime leftTime = LocalDateTime.now();
                    long wholeTime = ChronoUnit.SECONDS.between(vehicle.getEnteredTime(), leftTime);
                    System.out.println("You have been in parking : " + wholeTime + " seconds. Sum of payment for second : ");
                    VehicleType vehicleType1 = vehicle.getVehicleType();
                    switch (vehicleType1) {
                        case SEDANS -> {
                            System.out.print(parkingWithId.getSedanPrice());
                            System.out.println("\nBill : " + wholeTime * parkingWithId.getSedanPrice());
                            vehicle.setLeftTime(LocalDateTime.now());
                            vehicle.setState(-1);
                            vehicle.setInPark(false);
                            History history = new History(vehicle.getLicenceNumber(), vehicle.getEnteredTime(), LocalDateTime.now(),
                                    wholeTime * parkingWithId.getSedanPrice(), wholeTime, parkingId);
                            writeHistory(history);
                            vehicleService.update(vehicle);
                            return;
                        }
                        case TRUCKS -> {
                            vehicle.setLeftTime(LocalDateTime.now());
                            System.out.println(parkingWithId.getTruckPrice());
                            System.out.println("Bill : " + wholeTime * parkingWithId.getTruckPrice());
                            vehicle.setState(-1);
                            vehicle.setInPark(false);
                            return;
                        }
                        case MOTORBIKES -> {
                            vehicle.setLeftTime(LocalDateTime.now());
                            System.out.println(parkingWithId.getMotoPrice());
                            System.out.println("Bill : " + wholeTime * parkingWithId.getMotoPrice());
                            vehicle.setState(-1);
                            vehicle.setInPark(false);
                            return;
                        }
                    }
                    System.out.println("done");
                }
            } catch (Exception e) {
                System.out.println("Wrong input try again");
                scanner.nextLine();
            }
        }
    }

    public ArrayList<Vehicle> freePlace(UUID parkingId) throws IOException {
        ArrayList<Vehicle> vehicleArrayList = readVehicle();
        if (vehicleArrayList.size() > 0) {
            int i = 1;
            for (Vehicle vehicle : vehicleArrayList) {
                if (vehicle.getParkingId().equals(parkingId) && vehicle.isInPark()) {
                    System.out.println(i++ + ". Car number: " + vehicle.getLicenceNumber() + " || Car type : " + vehicle.getVehicleType()
                            + " || State : " + vehicle.getState() + "\n");
                }
            }
        }
        return vehicleArrayList;
    }

    public void enterCar(UUID parkingId) throws IOException {
        Parking parkingWithId = parkingService.getParkingWithId(parkingId);
        String type = """
                1. Sedan
                2. Truck
                3. Motorbike
                0. Back
                """;
        while (true) {
            System.out.print("Enter licence number of vehicle (0.back): ");
            carNumber = scannerStr.nextLine();
            if (carNumber.equals("0")) {
                return;
            }
            if (licenceNumberValidation.validNumber(carNumber)) {
                break;
            } else {
                System.out.println("Wrong type (01701PPP)");
            }

        }
        while (true) {
            System.out.print("choose type of vehicle (0.back) : \n");
            System.out.println(type);
            try {
                inputInt = scanner.nextInt();
                switch (inputInt) {
                    case 1 -> vehicleType = VehicleType.SEDANS;
                    case 2 -> vehicleType = VehicleType.TRUCKS;
                    case 3 -> vehicleType = VehicleType.MOTORBIKES;
                    case 0 -> {
                        return;
                    }
                    default -> {
                        System.out.println("Wrong input");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
        while (true) {
            int existLicenceNumber = isExistLicenceNumber(carNumber, vehicleType, parkingId);
            switch (existLicenceNumber) {
                case 1 -> {
                    System.out.println("Sorry there is car with this number in our parking");
                    return;
                }
                case -1 -> {
                    int choose = giveStateToVehicle(parkingId, parkingWithId);
                    if (choose > 0) {
                        Vehicle vehicleByNumber = getVehicleByNumber(carNumber);
                        vehicleByNumber.setInPark(true);
                        vehicleByNumber.setEnteredTime(LocalDateTime.now());
                        vehicleByNumber.setLeftTime(LocalDateTime.now());
                        vehicleByNumber.setParkingId(parkingId);
                        vehicleByNumber.setState(choose);
                        vehicleService.update(vehicleByNumber);
                    } else {
                        System.out.println("Process failed");
                    }
                    return;
                }
                case 0 -> {
                    int choose = giveStateToVehicle(parkingId, parkingWithId);
                    if (choose > 0) {
                        Vehicle vehicle = new Vehicle(vehicleType, carNumber, LocalDateTime.now(), LocalDateTime.now(),
                                true, parkingId, System.currentTimeMillis());
                        vehicle.setState(choose);
                        vehicleService.addVehicle(vehicle);
                        return;
                    } else {
                        System.out.println("Process failed");
                    }
                }
            }
        }
    }

    public int isExistLicenceNumber(String licenceNumber, VehicleType vehicleType, UUID parkingId) {
        try {
            ArrayList<Vehicle> vehicleArrayList = readVehicle();
            if (vehicleArrayList.size() < 1) {
                return 0;
            }
            for (Vehicle vehicle : vehicleArrayList) {
                if (vehicle.getLicenceNumber().equals(licenceNumber) && vehicle.isInPark()) {
                    return 1;
                } else if (vehicle.getVehicleType().equals(vehicleType) &&
                        vehicle.getLicenceNumber().equals(licenceNumber) && !vehicle.isInPark()) {
                    return -1;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Vehicle getVehicleByNumber(String licenceNumber) {
        ArrayList<Vehicle> vehicleArrayList = null;
        try {
            vehicleArrayList = readVehicle();
            for (Vehicle vehicle : vehicleArrayList) {
                if (vehicle.getLicenceNumber().equals(licenceNumber)) {
                    return vehicle;
                }
            }
            throw new DataNotFoundException("There is not car with this number");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int giveStateToVehicle(UUID parkingId, Parking parkingWithId) {
        while (true) {
            System.out.println("Choose floor : " + "1-" + parkingWithId.getFloors());
            try {
                int floor = scanner.nextInt();
                if (floor > 0 && floor <= parkingWithId.getFloors()) {
                    try {
                        ArrayList<Integer> places = parkingService.freePlaces(floor, parkingWithId);
                        int maxValue = 0;
                        for (Integer place : places) {
                            System.out.print(place + ", ");
                            if (place > maxValue) {
                                maxValue = place;
                            }
                        }
                        System.out.println("Choose place : (-) back");
                        try {
                            int freePlace = scanner.nextInt();
                            if (freePlace <= maxValue) {
                                return freePlace;
                            }
                        } catch (Exception e) {
                            System.out.println("Wrong input");
                            scanner.nextLine();
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Wrong input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }
}

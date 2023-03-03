package uz.project.dataBase;

import uz.project.entity.Admin;
import uz.project.entity.Manager;
import uz.project.enums.VehicleType;
import uz.project.model.Parking;
import uz.project.model.Vehicle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class DataBase {
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);
    public static int inputInt;
    public static String inputStr;
    public static String login;
    public static String password;
    public static String carNumber;
    public static int space;
    public static VehicleType vehicleType;
    public static Manager manager = new Manager("Ali","123");
    public static String name;
    public static int floor;
    public static int row;
    public static int columns;
    public static double priceForTruck;
    public static double priceForSedans;
    public static double priceForMotorBikes;
}

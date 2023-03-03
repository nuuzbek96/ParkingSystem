package uz.project.model;


import uz.project.entity.Admin;

import java.util.UUID;

public class Parking {
    private String name;
    private int floors;
    private int rows;
    private int columns;
    private double motoPrice;
    private double truckPrice;
    private double sedanPrice;
    private int allSlots;
    private Admin admin;
    private UUID parkingId;

    public Parking(int floors, int rows, int columns, double motoPrice, double truckPrice, double sedanPrice,Admin admin,String name) {
        this.floors = floors;
        this.rows = rows;
        this.columns = columns;
        this.motoPrice = motoPrice;
        this.truckPrice = truckPrice;
        this.sedanPrice = sedanPrice;
        this.admin = admin;
        this.name = name;
    }

    public Parking() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public double getMotoPrice() {
        return motoPrice;
    }

    public void setMotoPrice(double motoPrice) {
        this.motoPrice = motoPrice;
    }

    public double getTruckPrice() {
        return truckPrice;
    }

    public void setTruckPrice(double truckPrice) {
        this.truckPrice = truckPrice;
    }

    public double getSedanPrice() {
        return sedanPrice;
    }

    public void setSedanPrice(double sedanPrice) {
        this.sedanPrice = sedanPrice;
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public void setParkingId(UUID parkingId) {
        this.parkingId = parkingId;
    }

    public int getAllSlots() {
        return allSlots;
    }

    public void setAllSlots(int allSlots) {
        this.allSlots = allSlots;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}

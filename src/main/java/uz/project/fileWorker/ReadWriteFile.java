package uz.project.fileWorker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import uz.project.entity.Admin;
import uz.project.entity.History;
import uz.project.model.Parking;
import uz.project.model.Vehicle;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class ReadWriteFile {
    public void writeAdmins(Admin admin) throws IOException {
        ArrayList<Admin> admins = readAdminList();
        admins.add(admin);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\Admins.json"));
        bufferedWriter.write(gson.toJson(admins));
        bufferedWriter.close();
    }
    public ArrayList<Admin> readAdminList() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<Admin>>() { }.getType();
        return gson.fromJson(new FileReader("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\Admins.json"),
                listType);
    }

    public void writeParking(Parking parking) throws IOException {
        ArrayList<Parking> parkings = readParking();
        parkings.add(parking);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\Parkings.json"));
        bufferedWriter.write(gson.toJson(parkings));
        bufferedWriter.close();
    }
    public ArrayList<Parking> readParking() throws FileNotFoundException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<ArrayList<Parking>>() { }.getType();
        return gson.fromJson(new FileReader("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\Parkings.json"),
                listType);
    }
    public void writeVehicle(ArrayList<Vehicle> vehicles) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\CarsHistory.json"),
                vehicles);
    }
    public ArrayList<Vehicle> readVehicle() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\CarsHistory.json"),
                new TypeReference<ArrayList<Vehicle>>() {
                });
    }

    public void writeHistory(History history) throws IOException {
        ArrayList<History> historyArrayList = readHistory();
        historyArrayList.add(history);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\ParkingHistory.json"),
                historyArrayList);
    }
    public ArrayList<History> readHistory() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("D:\\G16_4_modul\\ParkingSystem\\src\\main\\resources\\ParkingHistory.json"),
                new TypeReference<ArrayList<History>>() {
                });
    }
}
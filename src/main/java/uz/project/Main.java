package uz.project;

import uz.project.controller.MainController;
import uz.project.controller.ManagerController;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.mainMenu();
    }
}
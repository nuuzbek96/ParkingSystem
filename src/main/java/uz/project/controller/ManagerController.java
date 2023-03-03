package uz.project.controller;

import uz.project.service.ManagerService;
import uz.project.service.ParkingService;

import static uz.project.dataBase.DataBase.*;


public class ManagerController {
    ParkingService parkingService = new ParkingService();
    ManagerService managerService = new ManagerService();
    public void managerMenu() {
        while (true) {
            String menu = """
                    1. login
                    0.Exit
                    """;

            System.out.println(menu);
            try {
                inputInt = scanner.nextInt();
                if (inputInt == 1) {
                    if (managerService.login()) {
                        mainMenu();
                    }
                } else if (inputInt == 0) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("\nWrong input please try again\n");
                scanner.nextLine();
            }
        }
    }
    public void mainMenu(){
        String mainMenu = """
                1. Create Parking
                2. Show history
                0. Exit
                """;
        while (true){
            System.out.println("\n"+mainMenu);
            try {
                inputInt = scanner.nextInt();
                switch (inputInt){
                    case 1->parkingService.createParking();
                    case 2-> managerService.histories();
                    case 0->{
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Wrong input");
                scanner.nextLine();
            }
        }
    }

}

package uz.project.controller;

import uz.project.service.AdminService;

import static uz.project.dataBase.DataBase.inputInt;
import static uz.project.dataBase.DataBase.scanner;

public class AdminController {
    public void adminMenu(){
        AdminService adminService = new AdminService();
        String adminMenu = """
                    1. login
                    0. Exit
                     """;
        while (true) {
            System.out.println(adminMenu);
            try {
                inputInt = scanner.nextInt();
                if (inputInt == 1){
                    adminService.login();
                } else if (inputInt == 0) {
                    return;
                }else {
                    System.out.println("Wrong menu selected");
                }
            } catch (Exception e) {
                System.out.println("\nWrong input please try again\n");
                scanner.nextLine();
            }
        }
    }
}

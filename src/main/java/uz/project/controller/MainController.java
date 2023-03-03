package uz.project.controller;
import static uz.project.dataBase.DataBase.*;
public class MainController {
    public void mainMenu(){
        AdminController adminController = new AdminController();
        ManagerController managerController = new ManagerController();
        String mainMenu = """
                    1. Admin
                    2. Manager
                    0. Exit
                    """;
        while (true) {
            System.out.println(mainMenu);
            try {
                inputInt = scanner.nextInt();
                switch (inputInt){
                    case 1-> adminController.adminMenu();
                    case 2-> managerController.managerMenu();
                    case 0->{
                        return;
                    }
                    default ->{
                        System.out.println("Wrong menu selected");
                    }
                }
            } catch (Exception e) {
                System.out.println("Wrong input please try again");
                scanner.nextLine();
            }
        }
    }
}

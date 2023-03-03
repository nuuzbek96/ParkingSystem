package uz.project.validation;
import uz.project.entity.Admin;
import uz.project.exeption.DataNotFoundException;
import uz.project.fileWorker.ReadWriteFile;
import uz.project.service.AdminService;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static uz.project.dataBase.DataBase.*;
public class AdminValidation extends ReadWriteFile {
    public Admin isCurrentAdmin(String login, String password) throws FileNotFoundException {
        ArrayList<Admin> adminArrayList = readAdminList();
        if (adminArrayList.size()>0) {
            for (Admin admin : adminArrayList) {
                if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                    return admin;
                }
            }
        }
        throw new DataNotFoundException("Admin not found with this : ");
    }
}

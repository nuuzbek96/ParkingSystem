package uz.project.entity;

import java.util.UUID;

public class Admin {
    private UUID adminId;
    private String login;
    private String password;
    private String name;
    private UUID parkingId;

    public Admin() {
    }

    public Admin(UUID adminId, String login, String password, String name, UUID parkingId) {
        this.adminId = adminId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.parkingId = parkingId;
    }

    public Admin(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getParkingId() {
        return parkingId;
    }

    public void setParkingId(UUID parkingId) {
        this.parkingId = parkingId;
    }
}

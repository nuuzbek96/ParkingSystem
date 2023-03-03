package uz.project.entity;

import java.util.UUID;

public class Manager {
    private String login;
    private String password;

    public Manager(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Manager() {
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
}

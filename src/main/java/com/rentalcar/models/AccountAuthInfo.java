package com.rentalcar.models;

public class AccountAuthInfo {

    private String login;
    private String password;

    public AccountAuthInfo(String login, String password) {
        setPassword(password);
        setLogin(login);
    }

    public AccountAuthInfo() {
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

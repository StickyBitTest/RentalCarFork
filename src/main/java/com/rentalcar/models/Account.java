package com.rentalcar.models;

public class Account {

    private AccountAuthInfo authInfo;
    private String mail;
    private boolean isAdmin;

    public Account(AccountAuthInfo accountAuthInfo) {
        setAuthInfo(accountAuthInfo);
    }

    public Account() {
        authInfo = new AccountAuthInfo();
    }

    public AccountAuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AccountAuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setLogin(String login){
        authInfo.setLogin(login);
    }

    public String getLogin(){
        return authInfo.getLogin();
    }

    public void  setPassword(String password){
        authInfo.setPassword(password);
    }

    public String getPassword(){
        return authInfo.getPassword();
    }

}

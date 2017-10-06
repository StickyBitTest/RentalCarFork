package com.rentalcar.models.user;

/**
 * Class Account defines site user entity, his role, login information
 *
 */

public class Account extends User {

    private LoginInfo loginInfo;
    private boolean isAdmin;

    /**
     * Inner class for storing account login details
     */
    class LoginInfo {

        private String login;
        private String password;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getPassword() {
            return password;
        }


        /**
         * @param password md5 hash
         */
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public Account() {
        loginInfo = new LoginInfo();
    }

    public Account(LoginInfo loginInfo) {
        setLoginInfo(loginInfo);
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setLogin(String login){
        loginInfo.setLogin(login);
    }

    public String getLogin(){
        return loginInfo.getLogin();
    }

    public void  setPassword(String password){
        loginInfo.setPassword(password);
    }

    public String getPassword(){
        return loginInfo.getPassword();
    }

}

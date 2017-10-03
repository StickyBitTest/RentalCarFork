package com.rentalcar.models.builders;

import com.rentalcar.controllers.security.Crypto;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.Account;
import com.rentalcar.models.AccountAuthInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountBuilder extends AbstractBuilder{

    private static final String MAIL_PATTERN = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";

    public AccountBuilder(HttpServletRequest request){
        super(request);
    }

    public Account getAuthInfo(){
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(validLogin(login) && validPassword(password))
            return new Account(new AccountAuthInfo(login, Crypto.sha256(password)));
        return null;
    }

    public Account getAccount() {
        Account account = getAuthInfo();
        if (account != null){
            String mail = request.getParameter("email");
            String confirmedPassword = request.getParameter("confirmPassword");
            if(validMail(mail) &&
                    validConfirmedPassword(account.getPassword(), confirmedPassword)){
                account.setMail(mail);
                return account;
            }
        }
        return null;
    }

    private boolean validConfirmedPassword(String password, String confirmedPassword){
        if(validPassword(confirmedPassword) && Crypto.sha256(confirmedPassword).equals(password))
            return true;
        errorMessage = ErrorMessage.ERROR_PASSWORD_MATCHES;
        return false;
    }

    private boolean validLogin(String login){
        if(notEmpty(login) && login.length() > 1)
            return true;
        errorMessage = ErrorMessage.ERROR_LOGIN;
        return false;
    }

    private boolean validMail(String mail){
        if(notEmpty(mail)){
            Pattern pattern = Pattern.compile(MAIL_PATTERN);
            Matcher matcher = pattern.matcher(mail);
            if(matcher.matches())
                return true;
        }
        errorMessage = ErrorMessage.ERROR_EMAIL;
        return false;
    }

    private boolean validPassword(String password){
        if(notEmpty(password) && password.length() > 4)
            return true;
        errorMessage = ErrorMessage.ERROR_PASSWORD;
        return false;
    }

    private boolean notEmpty(String string) {
        return (string != null) && string.length() > 0;
    }

}
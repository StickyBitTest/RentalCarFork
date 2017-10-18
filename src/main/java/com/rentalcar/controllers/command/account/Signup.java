package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.constants.RequestConstants;
import com.rentalcar.controllers.services.AccountService;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.user.Account;
import com.rentalcar.constants.MessageWrapper.Error;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class Signup implements Command {

    private static final Logger log = Logger.getLogger(Signup.class);

    private static final String INFO_FMT = "Successful user sign up : %s ";

    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        Account account = getAccount(request);
        if(account !=null){
            AccountService service = AccountService.getInstance();
            if(service.signUp(account)){
                setUser(request, account);
                log.info( String.format(INFO_FMT, account.getLogin()));
            }else {
                setMessage(request, Error.USER_EXISTS);
            }
        }
        return  getPreviousView(request);
    }

    private Account getAccount(HttpServletRequest request){
        Account account = null;
        UserBuilder builder = new UserBuilder()
                .setFullName(request.getParameter(RequestConstants.UserAttributes.FULL_NAME))
                .setLogin(request.getParameter(RequestConstants.UserAttributes.LOGIN))
                .setEmail(request.getParameter(RequestConstants.UserAttributes.EMAIL))
                .setPassword(request.getParameter(RequestConstants.UserAttributes.PASSWORD))
                .setConfirmPassword(request.getParameter(RequestConstants.UserAttributes.CONFIRMED_PASSWORD));
        try {
            account = builder.getAccount();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setMessage(request, builder.getErrorMessage());
        }
        return account;
    }
}

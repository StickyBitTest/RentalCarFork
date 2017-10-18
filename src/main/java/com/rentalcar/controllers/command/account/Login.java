package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.constants.MessageWrapper.Error;
import com.rentalcar.constants.RequestConstants;
import com.rentalcar.controllers.services.AccountService;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.user.Account;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login implements Command {

    private final static Logger log = Logger.getLogger(Login.class);

    private final static String ERROR_FMT = "Unable to login with account : %s";

    public String  execute(HttpServletRequest request, HttpServletResponse response) {

        Account account = getAccount(request);
        if(account != null){
            AccountService service = AccountService.getInstance();
            if(service.logIn(account)){
                setUser(request, account);
            }else{
                log.error(String.format(ERROR_FMT, account.getLogin()));
                setMessage(request, Error.USERNAME_OR_PASSWORD);
            }
        }
        return getPreviousView(request);
    }

    private Account getAccount(HttpServletRequest request){
        Account account = null;
        UserBuilder userBuilder = new UserBuilder()
                .setLogin(request.getParameter(RequestConstants.UserAttributes.LOGIN))
                .setPassword(request.getParameter(RequestConstants.UserAttributes.PASSWORD));
        try {
            account = userBuilder.getAccount();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setMessage(request, Error.USERNAME_OR_PASSWORD);
        }
        return account;
    }
}

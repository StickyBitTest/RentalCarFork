package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.controllers.utils.LocalRedirect;
import com.rentalcar.controllers.services.AccountService;
import com.rentalcar.controllers.utils.SessionContext;
import com.rentalcar.models.Account;
import com.rentalcar.models.builders.AccountBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Signup implements Command, LocalRedirect, SessionContext {

    private static final Logger log = Logger.getLogger(Signup.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AccountBuilder builder = new AccountBuilder(request);
        Account account = builder.getAccount();
        if(account == null){
            setError(request, builder.getErrorMessage());
            //request.getSession().setAttribute("error", builder.getErrorMessage());
            log.error("Unable to get account info from request, cause - " + builder.getErrorMessage());
        }else{
            AccountService service = AccountService.getInstance();
            if(service.signUp(account)){
                request.getSession().setAttribute("user", account);
                log.info("Succesfull user sign up : " + account.getLogin());
            }else{
                setError(request, ErrorMessage.ERROR_USER_EXISTS);
                //request.getSession().setAttribute("error", "User already exists");
            }
        }
        response.sendRedirect(formUrl(request));
    }
}

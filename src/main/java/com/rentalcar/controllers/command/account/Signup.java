package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.controllers.utils.LocalRedirect;
import com.rentalcar.controllers.services.AccountService;
import com.rentalcar.controllers.utils.SessionContext;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.user.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Signup implements Command, LocalRedirect, SessionContext {

    private static final Logger log = Logger.getLogger(Signup.class);

    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        /*UserBuilder builder = new UserBuilder();
        Account account = null;
        try {
            account = builder.getAccount();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setError(request, e.getMessage());
        }
            AccountService service = AccountService.getInstance();
            if(service.signUp(account)){
                request.getSession().setAttribute("user", account);
                log.info("Succesfull user sign up : " + account.getLogin());
            }else{
                setError(request, ErrorMessage.ERROR_USER_EXISTS);
                //request.getSession().setAttribute("error", "User already exists");
            }
        response.sendRedirect(formUrl(request));*/
        return  "/jsp/index.jsp";
    }
}

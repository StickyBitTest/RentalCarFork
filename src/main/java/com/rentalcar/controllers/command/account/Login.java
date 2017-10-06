package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.controllers.utils.LocalRedirect;
import com.rentalcar.controllers.services.AccountService;
import com.rentalcar.controllers.utils.SessionContext;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.builders.UserBuilder;
import com.rentalcar.models.user.Account;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login implements Command, LocalRedirect, SessionContext {

    private final static Logger log = Logger.getLogger(Login.class);

    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserBuilder builder = new UserBuilder();
        builder
                .setLogin(request.getParameter("login"))
                .setPassword(request.getParameter("password"));
        Account account = null;
        try {
            account = builder.getAccount();
        } catch (EntityBuilderException e) {
            log.error(e.getMessage());
            setError(request, e.getMessage());
        }
        AccountService service = AccountService.getInstance();
        if(service.logIn(account)){
            request.getSession().setAttribute("user", account);
        }else{
            log.error("Unable to login : " + account);
            setError(request, ErrorMessage.ERROR_USERNAME_OR_PASSWORD);
          //  request.getSession().setAttribute("error", "Invalid username or password");
        }
        response.sendRedirect(formUrl(request));
    }





}

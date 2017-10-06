package com.rentalcar.controllers.command.admin;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.command.Error;
import com.rentalcar.models.user.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Cars implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        /*Account account = (Account) request.getSession().getAttribute("user");
        if(account != null && account.isAdmin()){
            String view = "/jsp/admin/cars.jsp";
            //RequestDispatcher dispatcher = request.getRequestDispatcher(view);
            //dispatcher.forward(request, response);
        }else {
            new Error().execute(request, response);
        }*/
        return  "/jsp/index.jsp";
    }
}

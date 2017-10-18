package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        cleanSession(request);
        return getPreviousView(request);
    }
}

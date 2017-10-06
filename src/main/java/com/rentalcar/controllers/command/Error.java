package com.rentalcar.controllers.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Error implements Command {

    public String  execute(HttpServletRequest request, HttpServletResponse response) {
        return "/jsp/404.jsp";
    }

}

package com.rentalcar.controllers.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index implements Command {
    public String  execute(HttpServletRequest request, HttpServletResponse response) {
        return  "/jsp/index.jsp";
    }
}

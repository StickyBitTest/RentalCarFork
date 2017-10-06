package com.rentalcar.controllers.command.admin;

import com.rentalcar.controllers.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Orders implements Command {
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return  "/jsp/index.jsp";
    }
}

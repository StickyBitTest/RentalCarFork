package com.rentalcar.controllers.command.base;

import com.rentalcar.controllers.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.rentalcar.constants.CommandConstants.Pages.*;

public class Error implements Command {

    public String  execute(HttpServletRequest request, HttpServletResponse response) {
        return ERROR;
    }

}

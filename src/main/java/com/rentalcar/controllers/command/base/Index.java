package com.rentalcar.controllers.command.base;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.utils.SessionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.rentalcar.constants.CommandConstants.Pages.*;

public class Index implements Command, SessionContext {

    private static final String DEFAULT_PATH = "";

    public String  execute(HttpServletRequest request, HttpServletResponse response) {
        setPreviousPath(request, DEFAULT_PATH);
        setPreviousView(request, INDEX);
        return INDEX ;
    }


}

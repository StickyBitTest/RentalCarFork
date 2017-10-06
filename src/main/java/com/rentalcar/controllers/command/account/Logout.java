package com.rentalcar.controllers.command.account;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.utils.LocalRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout implements Command, LocalRedirect {
    public String execute(HttpServletRequest request, HttpServletResponse response)  {
        return  "/jsp/index.jsp";
        //request.getSession().removeAttribute("user");
        //response.sendRedirect(formUrl(request));
    }
}

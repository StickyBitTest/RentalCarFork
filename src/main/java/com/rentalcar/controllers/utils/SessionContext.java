package com.rentalcar.controllers.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface SessionContext {
    ResourceBundle resource = ResourceBundle.getBundle("language");

    default void setError(HttpServletRequest request, String  message){
        request.getSession().setAttribute("error", resource.getString(message));

    }
}

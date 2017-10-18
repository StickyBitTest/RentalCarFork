package com.rentalcar.controllers.utils;

import com.rentalcar.models.user.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public interface SessionContext {

    interface SessionAttributes{
        String PREVIOUS_PATH = "previous_path";
        String PREVIOUS_VIEW = "previous_view";
        String ERROR = "error";
        String USER = "user";
        String TERM = "term";
    }

    ResourceBundle resource = ResourceBundle.getBundle("language");

    default void setMessage(HttpServletRequest request, String  message){
        request.getSession().setAttribute(SessionAttributes.ERROR, resource.getString(message));
    }

    default void setPreviousPath(HttpServletRequest request, String url){
        request.getSession().setAttribute(SessionAttributes.PREVIOUS_PATH, url);
    }

    default void setPreviousView(HttpServletRequest request, String view){
        request.getSession().setAttribute(SessionAttributes.PREVIOUS_VIEW, view);
    }

    default String getPreviousView(HttpServletRequest request){
        return (String)request.getSession().getAttribute(SessionAttributes.PREVIOUS_VIEW);
    }

    default void setUser(HttpServletRequest request, Account account){
        request.getSession().setAttribute(SessionAttributes.USER, account);
    }

    default Account getUser(HttpServletRequest request){
        return (Account) request.getSession().getAttribute(SessionAttributes.USER);
    }

    default void removeUser(HttpServletRequest request){
        request.getSession().removeAttribute(SessionAttributes.USER);
    }

    default void cleanSession(HttpServletRequest request){
        removeUser(request);
    }
}

package com.rentalcar.controllers.servlet;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.controllers.command.CommandFactory;
import com.rentalcar.controllers.utils.SessionContext;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Entry point for rental car web app
 */
@WebServlet(name = "RentalCarServlet", urlPatterns = "/rental/*", loadOnStartup = 1)
public class RentalCarServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(RentalCarServlet.class);
    private static final String BASE_URL = "/rental/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        invokePost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        invokeGet(request, response);
    }

    private void invokeGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(getView(request, response)).forward(request, response);
    }


    private void invokePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getView(request, response);
        response.sendRedirect(getPreviousPage(request));
    }

    private String getPreviousPage(HttpServletRequest request) {
        String previous = (String) request.getSession().getAttribute(SessionContext.SessionAttributes.PREVIOUS_PATH);
        return (previous == null || previous.length() == 0) ? BASE_URL :  BASE_URL + previous;
    }

    private String getView(HttpServletRequest request, HttpServletResponse response){
        log.info(request.getMethod() + " - " + request.getPathInfo());
        Command command = CommandFactory.getInstance().getCommand(request.getPathInfo());
        return command.execute(request, response);
    }

}

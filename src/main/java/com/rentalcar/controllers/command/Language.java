package com.rentalcar.controllers.command;

import com.rentalcar.controllers.utils.LocalRedirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Language implements Command, LocalRedirect{

    private static List<String> locales = new LinkedList();

    static {
       locales.add("en");
       locales.add("ru");
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");
        int index = locales.indexOf(language);
        if(index == -1)
            language = locales.get(0);
        request.getSession().setAttribute("lang", language);
        Locale.setDefault(new Locale(language));

        response.sendRedirect(formUrl(request));

    }
}

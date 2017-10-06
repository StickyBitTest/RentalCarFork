package com.rentalcar.controllers.command;

import com.rentalcar.controllers.utils.LocalRedirect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String language = getLanguage(request.getParameter("lang"));
        request.getSession().setAttribute("lang", language);
        Locale.setDefault(new Locale(language));
        return "/jsp/index.jsp"; //todo return to previous page
    }


    private String getLanguage(String param){
        String language = param;
        int index = locales.indexOf(language);
        if(index == -1)
            language = locales.get(0);
        return language;
    }
}

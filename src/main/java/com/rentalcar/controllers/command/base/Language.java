package com.rentalcar.controllers.command.base;

import com.rentalcar.controllers.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static com.rentalcar.constants.RequestConstants.BaseAttributes.*;

public class Language implements Command {

    private static List<String> locales = new LinkedList();

    static {
       locales.add("en");
       locales.add("ru");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String language = getLanguage(request.getParameter(LANG));
        request.getSession().setAttribute(LANG, language);
        Locale.setDefault(new Locale(language));
        return getPreviousView(request);
    }


    private String getLanguage(String lang){
        return (locales.indexOf(lang) == -1) ? locales.get(0) : lang;
    }
}

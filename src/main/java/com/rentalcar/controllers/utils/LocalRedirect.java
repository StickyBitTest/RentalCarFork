package com.rentalcar.controllers.utils;

import com.rentalcar.controllers.command.account.Signup;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public interface LocalRedirect {

    Logger log = Logger.getLogger(Signup.class);

    String DEFAULT_RETURN = "/rental/";

    default String formUrl(HttpServletRequest request){
        String returnUrl = "";
        try {
            URL refererUrl = new URL(request.getHeader("referer"));
            returnUrl = refererUrl.getPath();
        } catch (MalformedURLException e) {
            log.error("Malformed referer : " + e.getMessage());
        }
        if(returnUrl == null || returnUrl.length() == 0){
            log.error("Empty return url ");
            returnUrl = DEFAULT_RETURN;
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getScheme()).append("://").append(request.getServerName())
                .append(":").append(request.getServerPort()).append(returnUrl);
        log.info("Return to " + buffer.toString());
        return buffer.toString();
    }
}

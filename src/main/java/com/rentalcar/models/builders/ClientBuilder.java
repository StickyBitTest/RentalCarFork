package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.Client;
import com.rentalcar.models.CreditCard;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientBuilder extends AbstractBuilder {

    private static final String MAIL_PATTERN = "(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";

    ClientBuilder(HttpServletRequest request) {
        super(request);
    }

    public Client getClient() {
        Client client = null;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String passport = request.getParameter("passport");
        if(validName(name) && validEmail(email) && validPassport(passport)){
            CreditCardBuilder cardBuilder = new CreditCardBuilder(request);
            CreditCard creditCard =  cardBuilder.getCreditCard();
            if(creditCard != null) {
                client = new Client();
                client.setCard(creditCard);
                client.setEmail(email);
                client.setName(name);
                client.setPassport(passport);
            }else{
                errorMessage = cardBuilder.errorMessage;
            }
        }
        return client;
    }

    private boolean validPassport(String passport) {
        if(passport !=null && passport.length() > 5)
            return true;
        errorMessage = ErrorMessage.ERROR_CLIENT_PASSPORT;
        return false;
    }

    private boolean validEmail(String email) {
        if(email != null){
            Pattern pattern = Pattern.compile(MAIL_PATTERN);
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches())
                return true;
        }
        errorMessage = ErrorMessage.ERROR_EMAIL;
        return false;
    }

    private boolean validName(String name) {
        if(name != null && name.length() > 5)
            return true;
        errorMessage = ErrorMessage.ERROR_CLIENT_NAME;
        return false;
    }


}

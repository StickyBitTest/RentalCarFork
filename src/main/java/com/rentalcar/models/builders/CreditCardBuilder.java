package com.rentalcar.models.builders;

import com.rentalcar.controllers.utils.ErrorMessage;
import com.rentalcar.models.CreditCard;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class CreditCardBuilder extends AbstractBuilder{


    private static final Logger log = Logger.getLogger(CreditCardBuilder.class);

    CreditCardBuilder(HttpServletRequest request) {
        super(request);
    }

    public CreditCard getCreditCard() {
        CreditCard creditCard = null;
        String cardNumber = request.getParameter("card_number");
        Date expiredDate = getDate(request.getParameter("expires"));
        int cvv2 = getCvv2(request.getParameter("cvv2"));
        if(validNumber(cardNumber) && expiredDate != null && cvv2 > 0){
            creditCard = new CreditCard(cardNumber, expiredDate, cvv2);
        }
        return creditCard;
    }

    private int getCvv2(String cvv2) {
        int cvv = -1;
        if(cvv2 !=null && cvv2.length() == 3){
            cvv = Integer.valueOf(cvv2);
        }else {
            errorMessage = ErrorMessage.ERROR_CVV2;
        }
        return cvv;
    }

    private Date getDate(String expiredDateValue) {
        Date expiredDate = null;
        if(expiredDateValue != null){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            try {
                    expiredDate =dateFormat.parse(expiredDateValue);
                    Date tillDate = Date.from(LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
                    if(expiredDate.after(tillDate)) {
                        return expiredDate;
                    }else {
                        expiredDate = null;
                    }
            } catch (ParseException e) {
                log.error("Exception parsing " + expiredDateValue + " : " + e.getMessage());
            }
        }
        errorMessage = ErrorMessage.ERROR_CARD_EXPIRES_DATE;
        return expiredDate;

    }


    private boolean validNumber(String cardNumber) {
        if(cardNumber != null && cardNumber.length() >=16)
            return true;
        errorMessage = ErrorMessage.ERROR_CARD_NUMBER;
        return false;
    }
}

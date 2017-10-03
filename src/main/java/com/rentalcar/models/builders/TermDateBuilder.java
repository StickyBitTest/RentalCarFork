package com.rentalcar.models.builders;

import com.rentalcar.models.TermDate;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TermDateBuilder extends AbstractBuilder{

    private static final Logger log = Logger.getLogger(TermDateBuilder.class);

    private SimpleDateFormat dateFormat;

    public TermDateBuilder(HttpServletRequest request){
        super(request);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public TermDate getTermDate(){
        TermDate termDate = null;
        String pickUpValue = request.getParameter("startDate");
        String dropOffValue = request.getParameter("endDate");
        if(pickUpValue != null && dropOffValue != null){
            try {
                Date pickUpDate = dateFormat.parse(pickUpValue);
                Date dropOffDate = dateFormat.parse(dropOffValue);
                if (pickUpDate.after(dropOffDate) || pickUpDate.compareTo(dropOffDate) == 0)
                    return termDate;
                termDate = new TermDate(pickUpDate, dropOffDate);
            } catch (ParseException e) {
                log.error("Error of parsing date");
            }

        }
        return termDate;
    }
}

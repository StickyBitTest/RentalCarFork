package com.rentalcar.models.builders;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractBuilder {

    protected String errorMessage;
    protected HttpServletRequest request;

    AbstractBuilder(HttpServletRequest request){
        this.request = request;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}

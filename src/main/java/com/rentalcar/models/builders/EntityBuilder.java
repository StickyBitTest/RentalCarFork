package com.rentalcar.models.builders;

import com.rentalcar.models.builders.validators.Validator;

public abstract class EntityBuilder implements Validator {

    protected String errorMessage;
    protected boolean isError;


    public String getErrorMessage(){
        return errorMessage;
    }

    protected void validate() throws EntityBuilderException{
        if(isError)
            throw new EntityBuilderException(this.errorMessage);
    }

    protected void validate(boolean result, String errorMessage){
        if(result == false){
            this.isError = true;
            this.errorMessage = errorMessage;
        }
    }
}

package com.rentalcar.models.builders.validators;

import java.math.BigDecimal;

public interface CarValidator extends Validator {

    default boolean isModelValid(String model){
        return !isEmpty(model);
    }

    default boolean isPriceValid(BigDecimal price){
        return price.compareTo(BigDecimal.ZERO) > 0;
    }
}

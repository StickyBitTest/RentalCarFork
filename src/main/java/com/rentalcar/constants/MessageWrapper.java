package com.rentalcar.constants;

public interface MessageWrapper {

    interface Error{

        String USER_EXISTS = "ERROR_USER_EXISTS";
        String USERNAME_OR_PASSWORD = "ERROR_USERNAME_OR_PASSWORD";
        String PASSWORD = "ERROR_PASSWORD";
        String EMAIL = "ERROR_EMAIL";
        String LOGIN = "ERROR_LOGIN";
        String PASSWORD_MATCHES = "ERROR_PASSWORD_MATCHES";
        String CLIENT_PASSPORT = "ERROR_CLIENT_PASSPORT";

        String CARD_NUMBER = "ERROR_CARD_NUMBER";
        String CARD_EXPIRES_DATE = "ERROR_CARD_EXPIRES_DATE";
        String CVV2 = "ERROR_CVV2";
        String PRICE = "ERROR_PRICE";
        String FULL_NAME = "USER.ERROR.FULL_NAME";

        String CREATE_CAR = "ERROR.CREATE_CAR";
        String UPLOAD_FILE = "ERROR.UPLOAD_FILE";

        String EMPTY_ORDERS = "INFO.EMPTY_ORDER_LIST";
        String CREATE_ORDER = "ERROR.CREATE_ORDER";
        String ORDER_CAR = "ERROR_ORDER_CAR";
        String ORDER_DATE = "ERROR_ORDER_DATE";
        String ORDER_CLIENT = "ERROR_ORDER_CLIENT";
        String ORDER_PRICE = "ERROR_ORDER_PRICE";
        String ORDER_STATUS = "ERROR_ORDER_STATUS";

        String CAR_MODEL = "ERROR_CAR_MODEL";
        String CAR_PRICE = "ERROR_CAR_PRICE";
        String CAR_TRANSMISSION = "ERROR_CAR_TRANSMISSION_TYPE";
        String CAR_VEHICLE = "ERROR_CAR_VEHICLE";
        String EMPTY_CARS = "ERROR_NO_CARS";


        String INVALID_DATE = "ERROR_INVALID_DATE";


    }

    interface Info {
        String CREATE_ORDER = "INFO.CREATE_ORDER";

    }
}

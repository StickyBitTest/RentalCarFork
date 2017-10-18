package com.rentalcar.constants;


/**
 * Provide constants for request attributes
 */
public interface RequestConstants {

    interface BaseAttributes {
        String LANG = "lang";
    }

    interface UserAttributes {
        String LOGIN = "login";
        String PASSWORD = "password";
        String FULL_NAME = "full_name";
        String EMAIL = "email";
        String CONFIRMED_PASSWORD = "confirmed_password";

        String CLIENT_PASSPORT = "passport";
        String CLIENT_CARD_NUMBER = "card_number";
        String CLIENT_CARD_EXPIRES = "card_expires";
        String CLIENT_CARD_CVV = "cvv2";
    }

    interface CarAttributes {
        String CAR_MODEL = "model";
        String CAR_TRANSMISSION = "transmission";
        String CAR_VEHICLE = "vehicle";
        String CAR_DAILY_PRICE = "daily_price";
        String CAR_IMG = "img_file";
        String CAR_ID = "car_id";
        String CAR_LIST = "cars";
    }

    interface OrderAttributes {
        String ORDER_LIST = "orders";
    }

    interface TermAttributes {
        String PICK_UP = "startDate";
        String DROP_OFF = "endDate";
        String RENT_DAYS = "days";
        String TERM_PERIOD = "term";

    }
}

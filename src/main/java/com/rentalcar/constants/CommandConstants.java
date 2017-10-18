package com.rentalcar.constants;

public interface CommandConstants {

    interface Names {

        String BASE = "";
        String ERROR = "Error";
        String LANG = "Language";

        String LOGIN = "Login";
        String LOGOUT = "Logout";
        String SIGN_UP = "Signup";

        String FIND = "Search";
        String RENT_CAR  ="NewOrder";

        String ADD_CAR = "AddCar";
        String SHOW_ORDERS = "ViewOrders";
    }

    interface Pages{

        String INDEX = "/jsp/index.jsp";
        String ERROR = "/jsp/404.jsp";
        String ORDERS = "/jsp/admin/orders.jsp";
        String CARS = "/jsp/rental/cars.jsp";

    }
}

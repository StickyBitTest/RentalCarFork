package com.rentalcar.controllers.command.admin;

import com.rentalcar.constants.MessageWrapper;
import com.rentalcar.controllers.command.Command;
import com.rentalcar.constants.CommandConstants;
import com.rentalcar.controllers.services.AdminService;
import com.rentalcar.models.order.Order;
import com.rentalcar.models.user.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import com.rentalcar.constants.RequestConstants.OrderAttributes;
import com.rentalcar.constants.CommandConstants.Pages;

public class ViewOrders implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Account user = getUser(request);
        if(user == null || !user.isAdmin())
            return CommandConstants.Pages.ERROR;
        List<Order> orders = AdminService.getInstance().getOrders();
        if(orders == null || orders.size() == 0){
            setMessage(request, MessageWrapper.Error.EMPTY_ORDERS);
        }else {
            request.setAttribute(OrderAttributes.ORDER_LIST, orders);
        }
        return Pages.ORDERS;
    }
}

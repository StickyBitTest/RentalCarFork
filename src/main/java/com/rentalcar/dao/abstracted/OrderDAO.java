package com.rentalcar.dao.abstracted;

import com.rentalcar.models.order.Order;

import java.util.List;

public interface OrderDAO {

    int add(Order order);

    List<Order> getAll();
}

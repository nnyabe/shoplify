package com.shoplify.shoplify.interfaces;

import com.shoplify.shoplify.exception.OrderNotFoundException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;

import java.util.List;

public interface OrderInterface {
    List<Orders> getAllOrders(LocalUser user);

    Orders createOrder(LocalUser user, Orders order);

    void deleteOrder(LocalUser user, Long orderId) throws OrderNotFoundException;
}

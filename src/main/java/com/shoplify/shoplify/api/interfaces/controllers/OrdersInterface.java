package com.shoplify.shoplify.api.interfaces.controllers;

import com.shoplify.shoplify.exception.OrderNotFoundException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrdersInterface {
        ResponseEntity<List<Orders>> getAllOrders(LocalUser user);

        ResponseEntity<Orders> createOrder(LocalUser user, Orders order);

        ResponseEntity<String> deleteOrder(LocalUser user, Long orderId) throws OrderNotFoundException;
}

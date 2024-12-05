package com.shoplify.shoplify.service;

import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import com.shoplify.shoplify.models.dao.OrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Orders> getAllOrders(LocalUser user) {
        return orderDAO.findByUser(user);
    }
}

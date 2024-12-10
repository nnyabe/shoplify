package com.shoplify.shoplify.service;

import com.shoplify.shoplify.exception.OrderNotFoundException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import com.shoplify.shoplify.models.dao.OrderDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<Orders> getAllOrders(LocalUser user) {
        return orderDAO.findByUser(user);
    }

    public Orders createOrder(LocalUser user, Orders order) {
        order.setUser(user);
        order.setAddress(user.getAddresses().getFirst());
        return orderDAO.save(order);
    }

    public void deleteOrder(LocalUser user, Long orderId) throws OrderNotFoundException {
       List<Orders> optOrder =  orderDAO.findByUser(user);
       if(optOrder.size() > 0 && optOrder.contains(orderDAO.findById(orderId))) {
           orderDAO.deleteById(orderId);
       }

       else {
           throw new OrderNotFoundException();
       }
    }
}

package com.shoplify.shoplify.api.controller.order;


import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import com.shoplify.shoplify.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<Orders> getOrders(@AuthenticationPrincipal final LocalUser user) {

        return orderService.getAllOrders(user);
    }
}

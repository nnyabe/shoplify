package com.shoplify.shoplify.api.controller.order;


import com.shoplify.shoplify.api.interfaces.controllers.OrdersInterface;
import com.shoplify.shoplify.exception.OrderNotFoundException;
import com.shoplify.shoplify.models.LocalUser;
import com.shoplify.shoplify.models.Orders;
import com.shoplify.shoplify.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController implements OrdersInterface {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Orders>> getAllOrders(@AuthenticationPrincipal final LocalUser user) {
        System.out.println(user.getUsername());
        return ResponseEntity.ok(orderService.getAllOrders(user));
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Orders> createOrder(@AuthenticationPrincipal final LocalUser user ,@RequestBody final Orders order) {
        return ResponseEntity.ok(orderService.createOrder(user, order));
    }

    @Override
    @DeleteMapping("/{order_id}")
    public ResponseEntity<String> deleteOrder(@AuthenticationPrincipal final LocalUser user,@PathVariable final Long order_id) {
        try{
            orderService.deleteOrder(user, order_id);
            return ResponseEntity.ok().build();
        }catch (OrderNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}

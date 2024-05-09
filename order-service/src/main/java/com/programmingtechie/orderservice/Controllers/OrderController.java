package com.programmingtechie.orderservice.Controllers;


import com.programmingtechie.orderservice.Entities.Order;
import com.programmingtechie.orderservice.DTOS.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.programmingtechie.orderservice.Services.OrderService;

import javax.naming.NameNotFoundException;
import java.util.UUID;


@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    OrderService orderService;


    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping
    public ResponseEntity insertOrder(@RequestBody OrderRequest orderRequest) throws IllegalAccessException {
        Order order =new Order();
        order.setOrderLineItems(orderRequest.getItemList());
        order.setOrderNumber(UUID.randomUUID().toString());
        Order prod = orderService.createOrder(order);
        return ResponseEntity.ok(prod);
    }

    @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable("id") Long Id) throws NameNotFoundException {
        Order prod = orderService.getById(Id);
        return ResponseEntity.ok(prod);
    }

}

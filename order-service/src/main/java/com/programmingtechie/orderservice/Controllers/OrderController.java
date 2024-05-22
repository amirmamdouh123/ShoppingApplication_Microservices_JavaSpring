package com.programmingtechie.orderservice.Controllers;


import com.programmingtechie.orderservice.Entities.Order;
import com.programmingtechie.orderservice.DTOS.OrderRequest;
import com.programmingtechie.orderservice.Entities.OrderLineItem;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.programmingtechie.orderservice.Services.OrderService;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    Logger logger=Logger.getLogger("OrderController");

    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackInsertOrder")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<ResponseEntity> insertOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("insertOrder");
        Order order =new Order();
        order.setOrderLineItems(orderRequest.getItemList());
        order.setOrderNumber(UUID.randomUUID().toString());
        return CompletableFuture.supplyAsync(()->
                ResponseEntity.ok(orderService.createOrder(order)));
    }

    public CompletableFuture<ResponseEntity> fallbackInsertOrder(OrderRequest orderRequest, Throwable t){
        System.out.println("fallbackInsertOrder");

        Order order=new Order();
        order.setOrderNumber(t.getMessage());
        return CompletableFuture.supplyAsync(()->ResponseEntity.ok(order));
    }

        @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable("id") Long Id) throws NameNotFoundException {
        Order prod = orderService.getById(Id);
        return ResponseEntity.ok(prod);
    }

}

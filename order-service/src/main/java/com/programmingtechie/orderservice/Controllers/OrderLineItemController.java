package com.programmingtechie.orderservice.Controllers;

import com.programmingtechie.orderservice.Entities.OrderLineItem;
import com.programmingtechie.orderservice.Services.OrderLineItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@RestController
@RequestMapping("orderItem")
public class OrderLineItemController {
    @Autowired
    OrderLineItemService orderLineItemService;


    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(orderLineItemService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity insertOrder(@RequestBody OrderLineItem order){
        OrderLineItem prod = orderLineItemService.createOrder(order);
        return ResponseEntity.ok(prod);
    }

    @GetMapping("{id}")
    public ResponseEntity getByID(@PathVariable("id") Long Id) throws NameNotFoundException {
        System.out.println("id "+ Id);
        OrderLineItem prod = orderLineItemService.getById(Id);
        return ResponseEntity.ok(prod);
    }
}

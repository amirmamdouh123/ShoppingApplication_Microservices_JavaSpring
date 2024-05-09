package com.programmingtechie.orderservice.Repos;


import com.programmingtechie.orderservice.Entities.Order;
import com.programmingtechie.orderservice.Entities.OrderLineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemRepo extends JpaRepository<OrderLineItem,Long> {
}

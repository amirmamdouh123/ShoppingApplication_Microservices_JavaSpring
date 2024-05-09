package com.programmingtechie.orderservice.Repos;


import com.programmingtechie.orderservice.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {
}

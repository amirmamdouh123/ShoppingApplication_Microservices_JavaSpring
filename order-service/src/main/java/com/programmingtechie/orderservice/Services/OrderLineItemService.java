package com.programmingtechie.orderservice.Services;

import com.programmingtechie.orderservice.Entities.Order;
import com.programmingtechie.orderservice.Entities.OrderLineItem;
import com.programmingtechie.orderservice.Repos.OrderLineItemRepo;
import com.programmingtechie.orderservice.Repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class OrderLineItemService {

    Logger logger=Logger.getLogger("OrderLineItem");
    @Autowired
    OrderLineItemRepo orderRepo;

    public OrderLineItem createOrder(OrderLineItem orderRequest){
//        logger.info("order: "+orderRequest);
        return orderRepo.save(orderRequest);
    }
    public List<OrderLineItem> findAll(){
        return orderRepo.findAll();
    }

    public OrderLineItem getById(Long id) throws NameNotFoundException {
        Optional<OrderLineItem> orderLineItem=orderRepo.findById(id);
        if(orderLineItem.isPresent()){
            return orderLineItem.get();
        }
        else{
            throw new NameNotFoundException("Not found");
        }
    }

}

package com.programmingtechie.orderservice.Services;

import com.programmingtechie.orderservice.DTOS.CheckItemResponse;
import com.programmingtechie.orderservice.Entities.Order;
import com.programmingtechie.orderservice.Entities.OrderLineItem;
import com.programmingtechie.orderservice.Repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class OrderService {

    Logger logger=Logger.getLogger("OrderService");

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    WebClient.Builder webClientBuilder;


    @Transactional
    public Order createOrder(Order orderRequest) throws IllegalAccessException {
//        logger.info("order: " + orderRequest);

          List<String> SkyCodesList= getSkuCodesFromOrder(orderRequest);

//        String skuCodeURL= ReformatSkuCodesToURL(SkyCodesList);
            //check order items inside Inventory
        //http://inventory-service/inventory/isInStock?skuCode=product1&skuCode=product2
            List<CheckItemResponse> CheckedItem = webClientBuilder.build().get().uri("http://inventory-service/api/inventory/isInStock"
                            ,uriBuilder -> uriBuilder.queryParam("skuCode",SkyCodesList).build()
                           )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<CheckItemResponse>>(){})
                    .block();
//
//            if (isInStock == null || !isInStock) {
//                throw new IllegalStateException("Items are not available in inventory: " + item.getSkyCode());
//
                logger.info("CheckedItem: " + CheckedItem);

        assert CheckedItem != null;
        boolean isAllSkuCodesAvailable = CheckedItem.stream().allMatch(CheckItemResponse::getIsInStock); // list of boolean make AND between all values of stream


        if(isAllSkuCodesAvailable){
            orderRequest.getOrderLineItems().forEach((item)->{
                item.setOrder(orderRequest);     //store each OrederLineItem
            });
            return orderRepo.save(orderRequest);  //store the Order
        }
        String NotFoundItems = getAllNotFoundSkuCodesInString(CheckedItem);
        throw new IllegalAccessException(NotFoundItems +"SkuCode is not exist in the Inventory");
    }

    String getAllNotFoundSkuCodesInString(List<CheckItemResponse> checkedItems){
        List<CheckItemResponse> NotFoundItems =checkedItems.stream().filter(item-> !item.getIsInStock()).toList();
        StringBuilder stringBuilder=new StringBuilder();
        NotFoundItems.forEach((item)->{
            stringBuilder.append(item.getSkuCode());
            stringBuilder.append(" ");
        });
        return stringBuilder.toString();
    };


    public List<String> getSkuCodesFromOrder(Order orderRequest) throws IllegalAccessException {
        return orderRequest.getOrderLineItems().stream().map(OrderLineItem::getSkuCode).toList();
    }

//    String ReformatSkuCodesToURL(List<String> skuCodes) throws IllegalAccessException {
//    Optional<String> x =skuCodes.stream().reduce((i,index)->{
//        return i+"&"+"skuCode="+index;
//    });
//    if(x.isPresent()) {
//        return "skuCodes="+x.get();
//    }
//
//    throw new IllegalAccessException("No Products are specified");
//    }

    public Order getById(Long id) throws NameNotFoundException {
        Optional<Order> orderLineItem=orderRepo.findById(id);
        if(orderLineItem.isPresent()){
            return orderLineItem.get();
        }
        else{
            throw new NameNotFoundException("Not found");
        }
    }


    public List<Order> findAll() {
        return orderRepo.findAll();
    }
}

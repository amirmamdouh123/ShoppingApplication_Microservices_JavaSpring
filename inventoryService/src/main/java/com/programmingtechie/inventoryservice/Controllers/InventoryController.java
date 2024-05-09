package com.programmingtechie.inventoryservice.Controllers;


import com.programmingtechie.inventoryservice.Entities.Inventory;
import com.programmingtechie.inventoryservice.Services.InventoryService;
import com.programmingtechie.inventoryservice.Entities.Inventory;
import com.programmingtechie.inventoryservice.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("inventory")
public class InventoryController {


    @Autowired
    InventoryService inventoryService;


    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PostMapping
    public ResponseEntity insertOrder(@RequestBody Inventory orderRequest){

        Inventory prod = inventoryService.createOrder(orderRequest);
        return ResponseEntity.ok(prod);
    }

//    @GetMapping("{id}")
//    public ResponseEntity getByID(@PathVariable("id") Long Id) throws NameNotFoundException {
//        Inventory prod = inventoryService.getById(Id);
//        return ResponseEntity.ok(prod);
//    }

//    @GetMapping("/isInStock/{skuCode}")    //localhost:port?skuCode=fdaf&skuCode=rqewrf....
//    public ResponseEntity isInStock(@PathVariable("skuCode") String skuCode){
//        return ResponseEntity.ok(inventoryService.isInStock(skuCode));
//    }

    @GetMapping("/isInStock")    //localhost:port?skuCode=fdaf&skuCode=rqewrf....
    public ResponseEntity isInStock(@RequestParam("skuCode") List<String> skuCodes){
        return ResponseEntity.ok(inventoryService.isAllInStock(skuCodes));
    }

}

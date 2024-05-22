package com.programmingtechie.inventoryservice.Controllers;


import com.programmingtechie.inventoryservice.Entities.InventoryItem;
import com.programmingtechie.inventoryservice.Services.InventoryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

Logger logger = Logger.getLogger("InventoryController");
    @Autowired
    InventoryService inventoryService;


    @GetMapping
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PostMapping
    public ResponseEntity insertInventoryITem(@RequestBody InventoryItem inventoryItem){
        System.out.println("start");
        InventoryItem prod = inventoryService.createOrder(inventoryItem);
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
    @SneakyThrows
    public ResponseEntity isInStock(@RequestParam("skuCode") List<String> skuCodes){
        System.out.println("start Wait 5s");
        Thread.sleep(5000);
        System.out.println("end Wait 5s");
        return ResponseEntity.ok(inventoryService.isAllInStock(skuCodes));
    }

}

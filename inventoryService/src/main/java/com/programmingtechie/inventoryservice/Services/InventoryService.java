package com.programmingtechie.inventoryservice.Services;

import com.programmingtechie.inventoryservice.DTOS.CheckItemResponse;
import com.programmingtechie.inventoryservice.Entities.InventoryItem;
import com.programmingtechie.inventoryservice.Repos.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class InventoryService {

    Logger logger=Logger.getLogger("Inventory");
    @Autowired
    InventoryRepo inventoryRepo;

    public InventoryItem createOrder(InventoryItem inventory){
//        logger.info("order: "+orderRequest);
        return inventoryRepo.save(inventory);
    }
    public List<InventoryItem> findAll(){
        return inventoryRepo.findAll();
    }

    public InventoryItem getById(Long id) throws NameNotFoundException {
        Optional<InventoryItem> inventory=inventoryRepo.findById(id);
        if(inventory.isPresent()){
            return inventory.get();
        }
        else{
            throw new NameNotFoundException("Not found");
        }
    }

//    public List<CheckItemResponse> isAllInStock(List<String> skuCodes){
//       return skuCodes.stream().map((OneSkuCode)-> {
//            return CheckItemResponse.builder()
//                    .skuCode(OneSkuCode)
//                    .isInStock(isInStock(OneSkuCode))
//                    .build();
//        }).toList();
//    }

    public Boolean isOneItemInStock(InventoryItem inventoryItem){
        return inventoryItem.getQuantity()>0 ;
    }


    public List<InventoryItem> findBySkuCodeIn(List<String> skuCodes){
       return inventoryRepo.findBySkuCodeIn(skuCodes);
    }

    public List<String> getSuckCodeFromInventoryItem(List<InventoryItem> inventoryItems){
        return inventoryItems.stream().map((InventoryItem::getSkuCode)).toList();
    }

    public List<String> getNoTFoundSkuCodesFormInventory(List<String> allSkuCodes,List<String> inventorySkuCodes){
       return allSkuCodes.stream().filter((skucode)-> !inventorySkuCodes.contains(skucode)).toList();
    }

    public List<CheckItemResponse> isAllInStock(List<String> AllSkuCodes){
        System.out.println("start isAllInStock Service");

        List<CheckItemResponse> checkItemResponses=new ArrayList<>();

        List<InventoryItem> inventoryItems = findBySkuCodeIn(AllSkuCodes);

        List<String> skuCodeInventory =getSuckCodeFromInventoryItem(inventoryItems);

        List<String> NotFoundSkuCodes= getNoTFoundSkuCodesFormInventory(AllSkuCodes,skuCodeInventory);

        //return the found skuCodes
        inventoryItems.forEach((item) -> {
            checkItemResponses.add(
                    CheckItemResponse.builder()
                            .skuCode(item.getSkuCode())
                            .isInStock(isOneItemInStock(item))
                            .build());
        });

        //return the not found skuCodes
        NotFoundSkuCodes.forEach((notFoundItem)->{
            checkItemResponses.add(
                    CheckItemResponse.builder()
                            .skuCode(notFoundItem)
                            .isInStock(false)
                            .build());
        });

        return checkItemResponses ;
    }

}

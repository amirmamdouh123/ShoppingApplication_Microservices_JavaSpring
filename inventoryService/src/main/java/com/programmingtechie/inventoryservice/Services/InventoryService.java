package com.programmingtechie.inventoryservice.Services;

import com.programmingtechie.inventoryservice.DTOS.CheckItemResponse;
import com.programmingtechie.inventoryservice.Entities.Inventory;
import com.programmingtechie.inventoryservice.Repos.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class InventoryService {

    Logger logger=Logger.getLogger("Inventory");
    @Autowired
    InventoryRepo inventoryRepo;

    public Inventory createOrder(Inventory inventory){
//        logger.info("order: "+orderRequest);
        return inventoryRepo.save(inventory);
    }
    public List<Inventory> findAll(){
        return inventoryRepo.findAll();
    }

    public Inventory getById(Long id) throws NameNotFoundException {
        Optional<Inventory> inventory=inventoryRepo.findById(id);
        if(inventory.isPresent()){
            return inventory.get();
        }
        else{
            throw new NameNotFoundException("Not found");
        }
    }

    public List<CheckItemResponse> isAllInStock(List<String> skuCodes){
       return skuCodes.stream().map((OneSkuCode)-> {
            return CheckItemResponse.builder()
                    .skuCode(OneSkuCode)
                    .isInStock(isInStock(OneSkuCode))
                    .build();
        }).toList();
    }

    public Boolean isInStock(String skuCode){
        Optional<Inventory> inventory=inventoryRepo.findBySkuCode(skuCode);
        return inventory.isPresent() && inventory.get().getQuantity()>0 ;
    }


}

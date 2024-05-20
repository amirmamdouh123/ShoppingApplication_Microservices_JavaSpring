package com.programmingtechie.inventoryservice.Repos;


import com.programmingtechie.inventoryservice.Entities.InventoryItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<InventoryItem,Long> {
//    Optional<InventoryItem> findBySkuCode(String skyCode);
    List<InventoryItem> findBySkuCodeIn(List<String> skyCode);

}

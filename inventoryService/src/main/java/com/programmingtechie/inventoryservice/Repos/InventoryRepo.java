package com.programmingtechie.inventoryservice.Repos;


import com.programmingtechie.inventoryservice.Entities.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findBySkuCode(String skyCode);
}

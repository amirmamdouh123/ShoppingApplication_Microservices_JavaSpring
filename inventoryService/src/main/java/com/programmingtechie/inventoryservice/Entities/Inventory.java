package com.programmingtechie.inventoryservice.Entities;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "inventories" ) //schema is specified in url  ->  jdbc:mysql://localhost:3306/hr
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String skuCode;

    Integer quantity;




}

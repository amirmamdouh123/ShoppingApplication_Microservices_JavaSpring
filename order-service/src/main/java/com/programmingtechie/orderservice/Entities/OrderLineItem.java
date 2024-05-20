package com.programmingtechie.orderservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Table(name = "t_order_line_items" ,schema = "HR")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String skuCode;

    Integer quantity;

    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    @JsonIgnoreProperties("orderLineItems")
    Order order;

}

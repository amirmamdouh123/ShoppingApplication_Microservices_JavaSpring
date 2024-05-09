package com.programmingtechie.orderservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Table(name = "orders" ) //schema is specified in url  ->  jdbc:mysql://localhost:3306/hr
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String orderNumber;

    @OneToMany( mappedBy = "order" ,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    List<OrderLineItem> orderLineItems;

//    @Override
//    public String toString() {
//        return "Order{id=" + id + ", orderNumber='" + orderNumber + "'}";
//    }

}

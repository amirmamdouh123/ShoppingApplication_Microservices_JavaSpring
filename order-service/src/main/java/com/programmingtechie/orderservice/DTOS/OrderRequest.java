package com.programmingtechie.orderservice.DTOS;

import com.programmingtechie.orderservice.Entities.OrderLineItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {

    List<OrderLineItem> itemList;

}

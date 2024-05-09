package com.programmingtechie.orderservice.DTOS;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
public class CheckItemResponse {

    String skuCode;

    Boolean isInStock;

}

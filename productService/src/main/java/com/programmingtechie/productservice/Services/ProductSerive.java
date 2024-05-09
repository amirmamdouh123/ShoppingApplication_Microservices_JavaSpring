package com.programmingtechie.productservice.Services;

import com.programmingtechie.productservice.Entities.Product;
import com.programmingtechie.productservice.Repos.ProductRepo;
import com.programmingtechie.productservice.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Service

public class ProductSerive {

    Logger logger=Logger.getLogger("ProductService");
    @Autowired
    ProductRepo productRepo;

    public Product createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        logger.info("product: "+productRequest);
        return productRepo.save(product);
    }
    public List<Product> findAll(){
        return productRepo.findAll();
    }
}

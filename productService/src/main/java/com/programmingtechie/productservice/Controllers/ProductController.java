package com.programmingtechie.productservice.Controllers;

import com.programmingtechie.productservice.Entities.Product;
import com.programmingtechie.productservice.Repos.ProductRepo;
import com.programmingtechie.productservice.Services.ProductSerive;
import com.programmingtechie.productservice.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {


    @Autowired
    ProductSerive productSerive;


    @GetMapping(value = "get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productSerive.findAll());
    }

    @PostMapping("post")
    public ResponseEntity insertProduct(@RequestBody ProductRequest product){
        Product prod = productSerive.createProduct(product);
        return ResponseEntity.ok(prod);
    }



}

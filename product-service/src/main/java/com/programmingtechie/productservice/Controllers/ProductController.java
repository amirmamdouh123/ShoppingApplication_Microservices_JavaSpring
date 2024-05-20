package com.programmingtechie.productservice.Controllers;

import com.programmingtechie.productservice.Entities.Product;
import com.programmingtechie.productservice.Repos.ProductRepo;
import com.programmingtechie.productservice.Services.ProductSerive;
import com.programmingtechie.productservice.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductSerive productSerive;


    @GetMapping
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok(productSerive.findAll());
    }

    @PostMapping
    public ResponseEntity insertProduct(@RequestBody ProductRequest product){
        Product prod = productSerive.createProduct(product);
        return ResponseEntity.ok(prod);
    }



}

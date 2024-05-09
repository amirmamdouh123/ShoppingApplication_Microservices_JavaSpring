package com.programmingtechie.productservice.Repos;

import com.programmingtechie.productservice.Entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}

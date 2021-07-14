package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.ProductDto;

public interface ProductService {
    String addProductTransaction(ProductDto productDto);
}

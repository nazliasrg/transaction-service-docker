package com.microservices.transaction.service;

import com.microservices.transaction.model.dto.ProductDto;
import com.microservices.transaction.model.dto.StatusMessageDto;
import com.microservices.transaction.model.entity.ProductEntity;
import com.microservices.transaction.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addProductTransaction(ProductDto dto) {
        StatusMessageDto<ProductEntity> result = new StatusMessageDto<>();

        if(dto.getProductCode() != null ){
            ProductEntity productEntity = new ProductEntity();
            productEntity.setProductId(dto.getProductId());
            productEntity.setProductCode(dto.getProductCode());
            productEntity.setProductName(dto.getProductName());
            productEntity.setDescription(dto.getDescription());
            productEntity.setPrice(dto.getPrice());
            productEntity.setStock(dto.getStock());
            productEntity.setIsAvailable(dto.getIsAvailable());
            productEntity.setImgSrc(dto.getImgSrc());

            productRepository.save(productEntity);

            result.setMessage("Product Code " + productEntity.getProductCode() + " has been added successfully!");
            return result.getMessage();
        }
        else{
            result.setMessage("Product Code is empty!");
            return result.getMessage();
        }
    }
}

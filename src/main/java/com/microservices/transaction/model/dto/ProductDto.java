package com.microservices.transaction.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer productId;
    private String productCode;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private Integer isAvailable;
    private String imgSrc;

    @Override
    public String toString() {
        return "ProductDto{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", isAvailable=" + isAvailable +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }
}

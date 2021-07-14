package com.microservices.transaction.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_product_t")
public class ProductEntity {
    @Id
    private Integer productId;

    @Column
    private String productCode;

    @Column
    private String productName;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private Integer stock;

    @Column
    private Integer isAvailable;

    @Column
    private String imgSrc;

    public ProductEntity(String productCode, String productName, String description, Double price, Integer stock, Integer isAvailable, String imgSrc) {
        this.productCode = productCode;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.isAvailable = isAvailable;
        this.imgSrc = imgSrc;
    }
}

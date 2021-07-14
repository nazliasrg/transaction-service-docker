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
@Table(name = "tbl_customer_t")
public class CustomerEntity {
    @Id
    private Integer customerId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Column
    private Double saldo;

    @Column
    private Integer statusAccount;
}

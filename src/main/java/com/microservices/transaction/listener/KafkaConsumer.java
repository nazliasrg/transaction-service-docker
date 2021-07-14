package com.microservices.transaction.listener;

import com.microservices.transaction.model.dto.CustomerDto;
import com.microservices.transaction.model.dto.ProductDto;
import com.microservices.transaction.model.dto.StatusMessageDto;
import com.microservices.transaction.service.CustomerService;
import com.microservices.transaction.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @KafkaListener(topics = "CustomerTopic", groupId = "group_customer")
    public void consume(String customer){
        StatusMessageDto result = new StatusMessageDto<>();

        JSONObject customerJson = new JSONObject(customer);

        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(customerJson.getInt("customerId"));
        dto.setUsername(customerJson.getString("username"));
        dto.setPassword(customerJson.getString("password"));
        dto.setName(customerJson.getString("name"));
        dto.setEmail(customerJson.getString("email"));
        dto.setAddress(customerJson.getString("address"));
        dto.setPhoneNumber(customerJson.getString("phoneNumber"));
        dto.setStatusAccount(customerJson.getInt("statusAccount"));
        dto.setSaldo(customerJson.getDouble("saldo"));

        result.setMessage(customerService.addCustomerTransaction(dto));

        System.out.println(" ========================================================= " );
        System.out.println("\t Customer data : " + customer);
        System.out.println("\t " + result.getMessage());
        System.out.println(" ========================================================= ");
    }

    @KafkaListener(topics = "ProductTopic", groupId = "group_product", containerFactory = "productFactory")
    public void consumeProduct(String product){
        StatusMessageDto result = new StatusMessageDto<>();
        System.out.println("\t Product data : " + product);

        JSONObject productJson = new JSONObject(product);

        ProductDto dto = new ProductDto();
        dto.setProductId(productJson.getInt("productId"));
        dto.setProductCode(productJson.getString("productCode"));
        dto.setProductName(productJson.getString("productName"));
        dto.setDescription(productJson.getString("description"));
        dto.setPrice(productJson.getDouble("price"));
        dto.setStock(productJson.getInt("stock"));
        dto.setIsAvailable(productJson.getInt("isAvailable"));
        dto.setImgSrc(productJson.getString("imgSrc"));

        result.setMessage(productService.addProductTransaction(dto));

        System.out.println(" ========================================================= " );
        System.out.println("\t " + result.getMessage());
        System.out.println(" ========================================================= ");
    }
}

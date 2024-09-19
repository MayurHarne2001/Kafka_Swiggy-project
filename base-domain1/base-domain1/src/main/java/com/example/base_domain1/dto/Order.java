package com.example.base_domain1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String customerName;
    private String region;
    private String restaurant;
    private double price;
}

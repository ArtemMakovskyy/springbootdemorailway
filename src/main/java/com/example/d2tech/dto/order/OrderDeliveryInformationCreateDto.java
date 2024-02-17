package com.example.d2tech.dto.order;

import lombok.Data;

@Data
public class OrderDeliveryInformationCreateDto {
    private String city;
    private String street;
    private Integer house;
    private Integer floor;
    private Integer apartment;
    private String phone;
    private String additionally;
}

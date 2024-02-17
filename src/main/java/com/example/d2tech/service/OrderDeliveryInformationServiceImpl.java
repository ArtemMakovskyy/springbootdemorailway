package com.example.d2tech.service;


import com.example.d2tech.repository.OrderDeliveryInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderDeliveryInformationServiceImpl {
    private final OrderDeliveryInformationRepository orderDeliveryInformationRepository;
}

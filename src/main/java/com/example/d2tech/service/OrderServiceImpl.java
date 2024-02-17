package com.example.d2tech.service;


import com.example.d2tech.model.Order;
import com.example.d2tech.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl {
   private final OrderRepository orderRepository;

   public Order save(){
// TODO: 05.02.2024
       return new Order();
   }
}

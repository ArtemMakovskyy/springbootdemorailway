package com.example.d2tech.repository;

import com.example.d2tech.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

//    Optional<PurchaseObject> findById(Long id);

}

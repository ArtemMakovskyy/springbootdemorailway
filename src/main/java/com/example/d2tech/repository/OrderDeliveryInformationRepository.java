package com.example.d2tech.repository;

import com.example.d2tech.model.OrderDeliveryInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDeliveryInformationRepository extends JpaRepository<OrderDeliveryInformation, Long> {

//    Optional<PurchaseObject> findById(Long id);

}

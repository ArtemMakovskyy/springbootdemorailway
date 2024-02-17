package com.example.d2tech.repository;

import com.example.d2tech.model.PurchaseObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseObjectRepository extends JpaRepository<PurchaseObject, Long> {

//    Optional<PurchaseObject> findById(Long id);

}

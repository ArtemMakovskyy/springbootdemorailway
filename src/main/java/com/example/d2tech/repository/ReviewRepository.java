package com.example.d2tech.repository;

import com.example.d2tech.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    Optional<PurchaseObject> findById(Long id);

}

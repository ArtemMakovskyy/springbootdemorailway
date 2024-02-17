package com.example.d2tech.repository;

import com.example.d2tech.model.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Long> {

}

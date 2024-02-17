package com.example.d2tech.repository;

import com.example.d2tech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}

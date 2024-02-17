package com.example.d2tech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "test_rntities")
public class TestEntity {

    @Id
    private Long id;

    private String info;
}

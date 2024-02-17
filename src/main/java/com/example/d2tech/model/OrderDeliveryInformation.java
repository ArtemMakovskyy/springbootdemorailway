package com.example.d2tech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders_delivery_information")
@Getter
@Setter
public class OrderDeliveryInformation {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private Integer house;
    private Integer floor;
    private Integer apartment;
    private String phone;
    private String additionally;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Order order;

}

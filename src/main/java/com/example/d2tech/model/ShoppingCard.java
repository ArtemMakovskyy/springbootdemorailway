package com.example.d2tech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shopping_cards")
@Getter
@Setter
public class ShoppingCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;


//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "shopping_card_id")
//    private Set<PurchaseObject> purchaseObjects;

    private BigDecimal totalCost;
}

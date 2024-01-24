package com.auctionex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "lots")
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false, name = "user_login", referencedColumnName = "login")
    private User userLogin;

    private String category;

    private BigDecimal startPrice;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<DeliveryOption> deliveryOptions;

    public Lot(User userLogin, String name, String category, BigDecimal startPrice) {
        this.userLogin = userLogin;
        this.name = name;
        this.category = category;
        this.startPrice = startPrice;
        this.deliveryOptions = new ArrayList<>();
    }
}

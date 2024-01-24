package com.auctionex.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "delivery_options")
public class DeliveryOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String firmName;

    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy="deliveryOptions")
    private List<Lot> lots;

    public DeliveryOption(String optionName, String firm, BigDecimal price) {
        this.name = optionName;
        this.firmName = firm;
        this.price = price;
    }
}

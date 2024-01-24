package com.auctionex.entity.offer.embedded_keys;

import com.auctionex.entity.bidding.Bidding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferEmbeddedKey implements Serializable{

    @ManyToOne()
    @JoinColumn(nullable = false, name= "bidding_id", referencedColumnName = "id")
    private Bidding biddingId;

    private BigDecimal amount;
}

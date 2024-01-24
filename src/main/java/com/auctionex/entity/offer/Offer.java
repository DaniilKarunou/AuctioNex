package com.auctionex.entity.offer;

import com.auctionex.entity.User;
import com.auctionex.entity.bidding.Bidding;
import com.auctionex.entity.offer.embedded_keys.OfferEmbeddedKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "offers")
public class Offer implements Serializable {

    @EmbeddedId
    private OfferEmbeddedKey offerEmbeddedKey;

    @ManyToOne()
    @JoinColumn(nullable = false, name= "user_login", referencedColumnName = "login")
    private User user;

    private ZonedDateTime timestamp;

    public Offer(Bidding biddingId, BigDecimal amount, User user) {
        this.offerEmbeddedKey = new OfferEmbeddedKey();
        this.offerEmbeddedKey.setBiddingId(biddingId);
        this.offerEmbeddedKey.setAmount(amount);
        this.user = user;
        this.timestamp = ZonedDateTime.now();
    }
}

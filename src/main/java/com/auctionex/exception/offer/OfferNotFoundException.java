package com.auctionex.exception.offer;

import java.math.BigDecimal;

public class OfferNotFoundException extends RuntimeException {
    public OfferNotFoundException(Integer biddingId, BigDecimal amount){
        super("Offer with bidding ID " + biddingId + " and amount " + amount + " not found");
    }
}

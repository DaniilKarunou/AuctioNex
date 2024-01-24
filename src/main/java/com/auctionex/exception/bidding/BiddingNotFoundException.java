package com.auctionex.exception.bidding;

public class BiddingNotFoundException extends RuntimeException {

    public BiddingNotFoundException(Integer biddingId) {
        super(String.format("Bidding with ID %d not found", biddingId));
    }
}

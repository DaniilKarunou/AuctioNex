package com.auctionex.exception.lot;

public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(Integer id) {
        super("Lot with id " + id + " not found.");
    }
}
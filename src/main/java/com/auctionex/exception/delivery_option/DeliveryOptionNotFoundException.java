package com.auctionex.exception.delivery_option;

public class DeliveryOptionNotFoundException extends RuntimeException {

    public DeliveryOptionNotFoundException(Integer id) {
        super("DeliveryOption with ID " + id + " not found");
    }
}

package com.auctionex.service;

import com.auctionex.entity.offer.Offer;

import java.math.BigDecimal;

public interface OfferService {

    /**
     * Saves an offer.
     *
     * @param offer The offer to be saved.
     * @return The saved offer.
     */
    Offer saveOffer(Offer offer);

    /**
     * Finds an offer by bidding ID and amount.
     *
     * @param biddingId The ID of the bidding associated with the offer.
     * @param amount    The amount of the offer.
     * @return The found offer or null if not found.
     */
    Offer findOfferByBiddingIdAndAmount(Integer biddingId, BigDecimal amount);

    /**
     * Gets all offers for a specific bidding.
     *
     * @param biddingId The ID of the bidding for which offers are retrieved.
     * @return An iterable collection of offers for the specified bidding.
     */
    Iterable<Offer> getOffersByBiddingId(Integer biddingId);
}

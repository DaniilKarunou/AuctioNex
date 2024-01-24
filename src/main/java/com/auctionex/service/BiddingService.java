package com.auctionex.service;

import com.auctionex.entity.bidding.Bidding;

public interface BiddingService {

    Bidding saveBidding(Bidding bidding);

    Bidding findBiddingById(Integer id);

    Iterable<Bidding> getAllBiddings();

    Iterable<Bidding> getAllBiddingsPaging(Integer pageNr, Integer howManyOnPage);

    void updateBidding(Bidding bidding);

    void deleteBidding(Integer id);
}


package com.auctionex.repository;

import com.auctionex.entity.offer.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<Offer, Integer>, PagingAndSortingRepository<Offer, Integer> {

    @Query("SELECT off FROM Offer off WHERE off.offerEmbeddedKey.biddingId = ?1 AND off.offerEmbeddedKey.amount = ?2")
    Optional<Offer> findOfferByBiddingIdAndAmount(Integer biddingId, BigDecimal amount);

    @Query("SELECT off FROM Offer off WHERE off.offerEmbeddedKey.biddingId = ?1")
    Iterable<Offer> findAllByBiddingId(Integer biddingId);
}

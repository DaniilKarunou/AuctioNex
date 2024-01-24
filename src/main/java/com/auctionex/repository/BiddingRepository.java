package com.auctionex.repository;

import com.auctionex.entity.bidding.Bidding;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface BiddingRepository extends CrudRepository<Bidding, Integer>, PagingAndSortingRepository<Bidding, Integer> {

    @Query("SELECT bid FROM Bidding bid WHERE bid.id = ?1")
    Optional<Bidding> findBiddingById(Integer id);
}

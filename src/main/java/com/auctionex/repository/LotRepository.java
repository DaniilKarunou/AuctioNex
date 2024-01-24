package com.auctionex.repository;


import com.auctionex.entity.DeliveryOption;
import com.auctionex.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface LotRepository extends JpaRepository<Lot, Integer>, PagingAndSortingRepository<Lot, Integer> {

    @Query("SELECT l FROM Lot l WHERE l.id = ?1")
    Optional<Lot> findLotById(Integer id);

    @Query("SELECT do FROM DeliveryOption do JOIN do.lots s WHERE s.id = ?1")
    List<DeliveryOption> getDeliveryOptionsByLotId(Integer id);
}

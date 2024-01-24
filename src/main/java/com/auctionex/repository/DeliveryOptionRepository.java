package com.auctionex.repository;

import com.auctionex.entity.DeliveryOption;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DeliveryOptionRepository extends CrudRepository<DeliveryOption, Integer>, PagingAndSortingRepository<DeliveryOption, Integer> {

    @Query("SELECT do FROM DeliveryOption do WHERE do.id = ?1")
    Optional<DeliveryOption> findDeliveryOptionById(Integer id);
}

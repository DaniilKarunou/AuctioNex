package com.auctionex.service.impl;

import com.auctionex.entity.bidding.Bidding;
import com.auctionex.exception.bidding.BiddingNotFoundException;
import com.auctionex.repository.BiddingRepository;
import com.auctionex.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BiddingServiceImpl implements BiddingService {

    private final BiddingRepository biddingRepository;

    @Autowired
    public BiddingServiceImpl(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public Bidding saveBidding(Bidding bidding) {
        return biddingRepository.save(bidding);
    }

    @Override
    public Bidding findBiddingById(Integer id) {
        Optional<Bidding> optionalBidding = biddingRepository.findById(id);
        if (optionalBidding.isPresent()) {
            return optionalBidding.get();
        } else {
            // Handle the case where the bidding with the given ID is not found
            throw new BiddingNotFoundException(id);
        }
    }

    @Override
    public Iterable<Bidding> getAllBiddings() {
        return biddingRepository.findAll();
    }

    @Override
    public Iterable<Bidding> getAllBiddingsPaging(Integer pageNr, Integer howManyOnPage) {
        return biddingRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

    @Override
    public void updateBidding(Bidding bidding) {
        // Check if the bidding with the given ID exists before updating
        if (biddingRepository.existsById(bidding.getId())) {
            biddingRepository.save(bidding);
        } else {
            // Handle the case where the bidding with the specified ID is not found
            throw new BiddingNotFoundException(bidding.getId());
        }
    }

    @Override
    public void deleteBidding(Integer id) {
        try {
            biddingRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the bidding with the given ID is not found
            throw new BiddingNotFoundException(id);
        }
    }
}

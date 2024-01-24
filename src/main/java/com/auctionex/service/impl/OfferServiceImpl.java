package com.auctionex.service.impl;

import com.auctionex.entity.offer.Offer;
import com.auctionex.exception.offer.OfferNotFoundException;
import com.auctionex.repository.OfferRepository;
import com.auctionex.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer findOfferByBiddingIdAndAmount(Integer biddingId, BigDecimal amount) {
        Optional<Offer> optionalOffer = offerRepository.findOfferByBiddingIdAndAmount(biddingId, amount);
        if (optionalOffer.isPresent()) {
            return optionalOffer.get();
        } else {
            throw new OfferNotFoundException(biddingId, amount);
        }
    }

    @Override
    public Iterable<Offer> getOffersByBiddingId(Integer biddingId) {
        return offerRepository.findAllByBiddingId(biddingId);
    }
}
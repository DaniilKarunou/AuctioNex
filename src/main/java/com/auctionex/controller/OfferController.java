package com.auctionex.controller;

import com.auctionex.entity.bidding.Bidding;
import com.auctionex.entity.offer.Offer;
import com.auctionex.entity.User;
import com.auctionex.exception.bidding.BiddingNotFoundException;
import com.auctionex.service.BiddingService;
import com.auctionex.service.OfferService;
import com.auctionex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final UserService userService;
    private final OfferService offerService;
    private final BiddingService biddingService;

    @Autowired
    public OfferController(
            UserService userService,
            OfferService offerService,
            BiddingService biddingService
    ) {
        this.userService = userService;
        this.offerService = offerService;
        this.biddingService = biddingService;
    }

    @GetMapping("/{biddingId}")
    public ResponseEntity<Iterable<Offer>> getOffersInBidding(@PathVariable Integer biddingId) {
        Iterable<Offer> offers = offerService.getOffersByBiddingId(biddingId);
        return ResponseEntity.ok(offers);
    }

    @PostMapping("/add")
    public ResponseEntity<Offer> addOffer(
            @RequestParam Integer biddingId,
            @RequestParam BigDecimal amount,
            @RequestParam String login
    ) {
        Bidding bidding;
        User user;
        try{
            bidding = biddingService.findBiddingById(biddingId);
        } catch (BiddingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        try{
            user = userService.findUserByLogin(login);
        } catch (BiddingNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        Offer offer = new Offer(bidding, amount, user);
        Offer savedOffer = offerService.saveOffer(offer);
        return ResponseEntity.ok(savedOffer);
    }
}
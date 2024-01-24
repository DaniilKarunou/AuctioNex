package com.auctionex.controller;

import com.auctionex.entity.bidding.Bidding;
import com.auctionex.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/biddings")
public class BiddingController {

    private final BiddingService biddingService;

    @Autowired
    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Iterable<Bidding>> getAllBiddings() {
        Iterable<Bidding> allBiddings = biddingService.getAllBiddings();
        return ResponseEntity.ok(allBiddings);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Iterable<Bidding>> getBiddingsPage (
            @RequestParam Integer page,
            @RequestParam Integer pageSize
    ) {
        Iterable<Bidding> biddingsPage = biddingService.getAllBiddingsPaging(page, pageSize);
        return ResponseEntity.ok(biddingsPage);
    }

    @GetMapping("/{biddingId}")
    public ResponseEntity<Bidding> getBiddingById(@PathVariable Integer biddingId) {
        Bidding bidding = biddingService.findBiddingById(biddingId);
        return bidding != null ? ResponseEntity.ok(bidding) : ResponseEntity.notFound().build();
    }

//    @PostMapping("/biddings/{biddingId}/offers/place")
//    public ResponseEntity<Void> placeOfferInBidding(@PathVariable Integer biddingId, @RequestBody Offer offer) {
//        if (biddingService.findBiddingById(biddingId) == null) {
//            return ResponseEntity.noContent().build();
//        }
//        biddingService.placeOfferInBidding(biddingId, offer);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @PutMapping("/{biddingId}/update")
    public ResponseEntity<Void> updateBidding(@PathVariable Integer biddingId, @RequestBody Bidding bidding) {
        if (biddingService.findBiddingById(biddingId) == null) {
            return ResponseEntity.noContent().build();
        }
        biddingService.saveBidding(bidding);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{biddingId}/delete")
    public ResponseEntity<Void> deleteBidding(@PathVariable Integer biddingId) {
        biddingService.deleteBidding(biddingId);
        return ResponseEntity.ok().build();
    }
}

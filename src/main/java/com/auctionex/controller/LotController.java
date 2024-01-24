package com.auctionex.controller;

import com.auctionex.dto.Message;
import com.auctionex.entity.DeliveryOption;
import com.auctionex.entity.Lot;
import com.auctionex.entity.User;
import com.auctionex.service.LotService;
import com.auctionex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final UserService userService;
    private final LotService lotService;

    @Autowired
    public LotController(
            UserService userService,
            LotService lotService
    ) {
        this.userService = userService;
        this.lotService = lotService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lot> getLot(@PathVariable Integer id) {
        Lot lot = lotService.findLotById(id);
        return lot != null ? ResponseEntity.ok(lot) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Lot>> getAllLots() {
        Iterable<Lot> allLots = lotService.getAllLots();
        return ResponseEntity.ok(allLots);
    }

    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<Iterable<Lot>> getLotsPage(
            @PathVariable Integer page,
            @PathVariable Integer pageSize
    ) {
        Iterable<Lot> allLots = lotService.getAllLotsPaging(page, pageSize);
        return ResponseEntity.ok(allLots);
    }

    @PostMapping("/create")
    public ResponseEntity<Lot> createLot(
            @RequestParam String userLogin,
            @RequestParam String subjectName,
            @RequestParam String category,
            @RequestParam BigDecimal startPrice
    ) {
        User user = userService.findUserByLogin(userLogin);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Lot lot = new Lot(user, subjectName, category, startPrice);
        Lot savedLot = lotService.saveLot(lot);
        return ResponseEntity.ok(savedLot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLot(@PathVariable Integer id) {
        lotService.deleteLot(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Message> editLot(@RequestBody Lot lot) {
        if (lotService.findLotById(lot.getId()) == null) {
            return ResponseEntity.noContent().build();
        }
        lotService.saveLot(lot);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/deliveryOptions/{id}")
    public ResponseEntity<List<DeliveryOption>> getLotDeliveryOptions(@PathVariable Integer id) {
        List<DeliveryOption> deliveryOptions = lotService.getDeliveryOptionsByLotId(id);
        return ResponseEntity.ok(deliveryOptions);
    }
}

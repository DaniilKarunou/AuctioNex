package com.auctionex.controller;

import com.auctionex.entity.DeliveryOption;
import com.auctionex.service.DeliveryOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
@RequestMapping("/api/deliveryOptions")
public class DeliveryOptionController {

    private final DeliveryOptionService deliveryOptionService;

    @Autowired
    public DeliveryOptionController(DeliveryOptionService deliveryOptionService) {
        this.deliveryOptionService = deliveryOptionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOption> getDeliveryOption(@PathVariable Integer id) {
        DeliveryOption deliveryOption = deliveryOptionService.findDeliveryOptionById(id);
        return deliveryOption != null ? ResponseEntity.ok(deliveryOption) : ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<Iterable<DeliveryOption>> getAllDeliveryOptions() {
        Iterable<DeliveryOption> allDeliveryOptions = deliveryOptionService.getAllDeliveryOptions();
        return ResponseEntity.ok(allDeliveryOptions);
    }

    @PostMapping("/add")
    public ResponseEntity<DeliveryOption> addDeliveryOption(
            @RequestParam String optionName,
            @RequestParam String firm,
            @RequestParam BigDecimal price
    ) {
        DeliveryOption deliveryOption = new DeliveryOption(optionName, firm, price);
        DeliveryOption savedDeliveryOption = deliveryOptionService.saveDeliveryOption(deliveryOption);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDeliveryOption.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedDeliveryOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryOption(@PathVariable Integer id) {
        deliveryOptionService.deleteDeliveryOption(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editDeliveryOption(@RequestBody DeliveryOption deliveryOption) {
        if (deliveryOptionService.findDeliveryOptionById(deliveryOption.getId()) == null) {
            return ResponseEntity.noContent().build();
        }
        deliveryOptionService.saveDeliveryOption(deliveryOption);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
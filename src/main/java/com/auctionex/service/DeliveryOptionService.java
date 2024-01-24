package com.auctionex.service;

import com.auctionex.entity.DeliveryOption;

public interface DeliveryOptionService {

    DeliveryOption saveDeliveryOption(DeliveryOption deliveryOption);

    DeliveryOption findDeliveryOptionById(Integer id);

    Iterable<DeliveryOption> getAllDeliveryOptions();

    Iterable<DeliveryOption> getAllDeliveryOptionsPaging(Integer pageNr, Integer howManyOnPage);

    void updateDeliveryOption(DeliveryOption deliveryOption);

    void deleteDeliveryOption(Integer id);
}

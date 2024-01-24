package com.auctionex.service.impl;

import com.auctionex.entity.DeliveryOption;
import com.auctionex.exception.delivery_option.DeliveryOptionNotFoundException;
import com.auctionex.repository.DeliveryOptionRepository;
import com.auctionex.service.DeliveryOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryOptionServiceImpl implements DeliveryOptionService {

    private final DeliveryOptionRepository deliveryOptionRepository;

    @Autowired
    public DeliveryOptionServiceImpl(DeliveryOptionRepository deliveryOptionRepository) {
        this.deliveryOptionRepository = deliveryOptionRepository;
    }

    @Override
    public DeliveryOption saveDeliveryOption(DeliveryOption deliveryOption) {
        return deliveryOptionRepository.save(deliveryOption);
    }

    @Override
    public DeliveryOption findDeliveryOptionById(Integer id) {
        Optional<DeliveryOption> optionalDeliveryOption = deliveryOptionRepository.findById(id);
        if (optionalDeliveryOption.isPresent()) {
            return optionalDeliveryOption.get();
        } else {
            throw new DeliveryOptionNotFoundException(id);
        }
    }

    @Override
    public Iterable<DeliveryOption> getAllDeliveryOptions() {
        return deliveryOptionRepository.findAll();
    }

    @Override
    public Iterable<DeliveryOption> getAllDeliveryOptionsPaging(Integer pageNr, Integer howManyOnPage) {
        return deliveryOptionRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

    @Override
    public void updateDeliveryOption(DeliveryOption deliveryOption) {
        if (deliveryOptionRepository.existsById(deliveryOption.getId())) {
            deliveryOptionRepository.save(deliveryOption);
        } else {
            throw new DeliveryOptionNotFoundException(deliveryOption.getId());
        }
    }

    @Override
    public void deleteDeliveryOption(Integer id) {
        try {
            deliveryOptionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DeliveryOptionNotFoundException(id);
        }
    }
}

package com.auctionex.service.impl;

import com.auctionex.entity.DeliveryOption;
import com.auctionex.entity.Lot;
import com.auctionex.exception.lot.LotNotFoundException;
import com.auctionex.repository.LotRepository;
import com.auctionex.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LotServiceImpl implements LotService {

    private final LotRepository lotRepository;

    @Autowired
    public LotServiceImpl(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    @Override
    public Lot saveLot(Lot lot) {
        return lotRepository.save(lot);
    }

    @Override
    public Lot findLotById(Integer id) {
        Optional<Lot> optionalLot = lotRepository.findLotById(id);
        if (optionalLot.isPresent()) {
            return optionalLot.get();
        } else {
            throw new LotNotFoundException(id);
        }
    }

    @Override
    public Iterable<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    @Override
    public Iterable<Lot> getAllLotsPaging(Integer pageNr, Integer howManyOnPage) {
        return lotRepository.findAll(PageRequest.of(pageNr, howManyOnPage));
    }

    @Override
    public void deleteLot(Integer id) {
        lotRepository.deleteById(id);
    }

    @Override
    public List<DeliveryOption> getDeliveryOptionsByLotId(Integer id) {
        return lotRepository.getDeliveryOptionsByLotId(id);
    }
}

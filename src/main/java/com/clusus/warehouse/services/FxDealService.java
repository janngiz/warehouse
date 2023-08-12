package com.clusus.warehouse.services;

import com.clusus.warehouse.entities.FxDeal;
import com.clusus.warehouse.repo.FxDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FxDealService {
    @Autowired
    private final FxDealRepository fxDealRepository;

    public FxDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FxDeal saveFxDeal(FxDeal fxDeal) {
        fxDeal.setId(UUID.randomUUID().toString());
        return fxDealRepository.save(fxDeal);
    }

    public FxDeal findByDealUniqueId(String dealUniqueId) {
        return fxDealRepository.findByDealUniqueId(dealUniqueId);
    }
}

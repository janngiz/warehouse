package com.clusus.warehouse.services;

import com.clusus.warehouse.entities.FxDeal;
import com.clusus.warehouse.exceptions.ValidationException;
import com.clusus.warehouse.repo.FxDealRepository;
import com.clusus.warehouse.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FxDealService {
    private static final Logger logger = LoggerFactory.getLogger(FxDealService.class);

    @Autowired
    private final FxDealRepository fxDealRepository;

    public FxDealService(FxDealRepository fxDealRepository) {
        this.fxDealRepository = fxDealRepository;
    }

    public FxDeal saveFxDeal(FxDeal fxDeal) {
        logger.info("Saving the fxDeal : {}", fxDeal);
        fxDealValidation(fxDeal);
        String id = fxDeal.getDealUniqueId();
        FxDeal byId = fxDealRepository.findByDealUniqueId(id);
        if (byId != null) {
            logger.warn("Fx deal with unique id {} already exists", id);
            throw new ValidationException("Already exist.");
        }
        fxDeal.setId(UUID.randomUUID().toString());
        return fxDealRepository.save(fxDeal);
    }

    //No rollback allowed, what every rows imported should be saved in DB
    public List<FxDeal> saveFxDealList(List<FxDeal> fxDealList) {
        logger.info("Saving the fxDealList : {}", fxDealList);
        List<FxDeal> savedFxDealList = new ArrayList<>();
        for (FxDeal fxDeal : fxDealList) {
            FxDeal savedFxDeal = fxDealSave(fxDeal);
            if (savedFxDeal != null) {
                savedFxDealList.add(savedFxDeal);
            }
        }
        return savedFxDealList;
    }

    private FxDeal fxDealSave(FxDeal fxDeal) {
        try {
            logger.info("Saving the fxDeal : {}", fxDeal);
            fxDealValidation(fxDeal);
            fxDeal.setId(UUID.randomUUID().toString());
            return fxDealRepository.save(fxDeal);
        } catch (Exception e) {
            return null;
        }
    }

    public FxDeal findByDealUniqueId(String dealUniqueId) {
        logger.info("Getting the fxDeal by id : {}", dealUniqueId);
        return fxDealRepository.findByDealUniqueId(dealUniqueId);
    }


    private void fxDealValidation(FxDeal fxDeal) {
        String dealUniqueId = fxDeal.getDealUniqueId();
        String fromCurrencyIsoCode = fxDeal.getFromCurrencyIsoCode();
        String toCurrencyIsoCode = fxDeal.getToCurrencyIsoCode();
        Long dealTimestamp = fxDeal.getDealTimestamp();
        BigDecimal dealAmount = fxDeal.getDealAmount();

        if (StringUtils.isNullOrEmpty(dealUniqueId)) {
            logger.warn("Deal unique id is null or empty");
            throw new ValidationException("Deal unique id cannot be null or empty. ");
        }
        if (StringUtils.isNullOrEmpty(fromCurrencyIsoCode)) {
            logger.warn("From Currency Iso code is null or empty");
            throw new ValidationException("From Currency Iso code cannot be null or empty. ");
        }
        if (StringUtils.isNullOrEmpty(toCurrencyIsoCode)) {
            logger.warn("To Currency Iso code is null or empty");
            throw new ValidationException("To Currency Iso code cannot be null or empty. ");

        }
        if (dealTimestamp == null || dealTimestamp == 0) {
            logger.warn("DealTimestamp is null or zero");
            throw new ValidationException("DealTimestamp cannot be null or zero. ");
        }

        if (dealAmount == null || dealAmount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.warn("DealAmount is null, zero, or negative");
            throw new ValidationException("DealAmount cannot be null or zero or negative. ");
        }

        if (!isValidIsoCurrencyCode(fromCurrencyIsoCode)) {
            logger.warn("From Currency Iso code is not valid: {}", fromCurrencyIsoCode);
            throw new ValidationException("From Currency Iso code is not valid. ");

        }
        if (!isValidIsoCurrencyCode(toCurrencyIsoCode)) {
            logger.warn("To Currency Iso code is not valid: {}", toCurrencyIsoCode);
            throw new ValidationException("To Currency Iso code is not valid. ");

        }

    }

    private boolean isValidIsoCurrencyCode(String currencyCode) {
        if (!currencyCode.matches("[A-Z]{3}")) {
            return false;
        }
        return true;
    }


}

package com.clusus.warehouse;

import com.clusus.warehouse.entities.FxDeal;
import com.clusus.warehouse.repo.FxDealRepository;
import com.clusus.warehouse.services.FxDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FxDealServiceTest {
    @Mock
    private FxDealRepository fxDealRepository;

    private FxDealService fxDealService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fxDealService = new FxDealService(fxDealRepository);
    }

    @Test
    public void testSaveFxDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealUniqueId("123");
        fxDeal.setFromCurrencyIsoCode("USD");
        fxDeal.setToCurrencyIsoCode("EUR");
        fxDeal.setDealTimestamp(123456789L);
        fxDeal.setDealAmount(new BigDecimal("100.00"));

        when(fxDealRepository.findByDealUniqueId("123")).thenReturn(null);
        when(fxDealRepository.save(fxDeal)).thenReturn(fxDeal);

        FxDeal savedFxDeal = fxDealService.saveFxDeal(fxDeal);

        assertEquals(fxDeal, savedFxDeal);

        verify(fxDealRepository, times(1)).findByDealUniqueId("123");
        verify(fxDealRepository, times(1)).save(fxDeal);
    }

    @Test
    public void testSaveFxDealList() {
        FxDeal fxDeal1 = new FxDeal();
        fxDeal1.setDealUniqueId("123");
        fxDeal1.setFromCurrencyIsoCode("USD");
        fxDeal1.setToCurrencyIsoCode("EUR");
        fxDeal1.setDealTimestamp(123456789L);
        fxDeal1.setDealAmount(new BigDecimal("100.00"));

        FxDeal fxDeal2 = new FxDeal();
        fxDeal2.setDealUniqueId("456");
        fxDeal2.setFromCurrencyIsoCode("GBP");
        fxDeal2.setToCurrencyIsoCode("JPY");
        fxDeal2.setDealTimestamp(987654321L);
        fxDeal2.setDealAmount(new BigDecimal("200.00"));

        List<FxDeal> fxDealList = Arrays.asList(fxDeal1, fxDeal2);

        when(fxDealRepository.saveAll(fxDealList)).thenReturn(fxDealList);

        List<FxDeal> savedFxDeals = fxDealService.saveFxDealList(fxDealList);

        assertEquals(fxDealList, savedFxDeals);

        verify(fxDealRepository, times(1)).saveAll(fxDealList);
    }

    @Test
    public void testFindByDealUniqueId() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealUniqueId("123");
        fxDeal.setFromCurrencyIsoCode("USD");
        fxDeal.setToCurrencyIsoCode("EUR");
        fxDeal.setDealTimestamp(123456789L);
        fxDeal.setDealAmount(new BigDecimal("100.00"));

        when(fxDealRepository.findByDealUniqueId("123")).thenReturn(fxDeal);

        FxDeal foundFxDeal = fxDealService.findByDealUniqueId("123");

        assertEquals(fxDeal, foundFxDeal);

        verify(fxDealRepository, times(1)).findByDealUniqueId("123");
    }

    @Test
    public void testGetAllFxDeals() {
        FxDeal fxDeal1 = new FxDeal();
        fxDeal1.setDealUniqueId("123");
        fxDeal1.setFromCurrencyIsoCode("USD");
        fxDeal1.setToCurrencyIsoCode("EUR");
        fxDeal1.setDealTimestamp(123456789L);
        fxDeal1.setDealAmount(new BigDecimal("100.00"));

        FxDeal fxDeal2 = new FxDeal();
        fxDeal2.setDealUniqueId("456");
        fxDeal2.setFromCurrencyIsoCode("GBP");
        fxDeal2.setToCurrencyIsoCode("JPY");
        fxDeal2.setDealTimestamp(987654321L);
        fxDeal2.setDealAmount(new BigDecimal("200.00"));

        List<FxDeal> fxDeals = Arrays.asList(fxDeal1, fxDeal2);

        when(fxDealRepository.findAll()).thenReturn(fxDeals);

        List<FxDeal> foundFxDeals = fxDealService.getAllFxDeals();

        assertEquals(fxDeals, foundFxDeals);

        verify(fxDealRepository, times(1)).findAll();
    }
}



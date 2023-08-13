package com.clusus.warehouse;

import com.clusus.warehouse.controllers.FxDealController;
import com.clusus.warehouse.entities.FxDeal;
import com.clusus.warehouse.services.FxDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FxDealControllerTest {
    @Mock
    private FxDealService fxDealService;

    private FxDealController fxDealController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fxDealController = new FxDealController(fxDealService);
    }

    @Test
    public void testCreateFxDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealUniqueId("123");
        fxDeal.setFromCurrencyIsoCode("USD");
        fxDeal.setToCurrencyIsoCode("EUR");
        fxDeal.setDealTimestamp(123456789L);
        fxDeal.setDealAmount(new BigDecimal("100.00"));

        when(fxDealService.saveFxDeal(fxDeal)).thenReturn(fxDeal);
        ResponseEntity<FxDeal> response = fxDealController.createFxDeal(fxDeal);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fxDeal, response.getBody());

        verify(fxDealService, times(1)).saveFxDeal(fxDeal);
    }

    @Test
    public void testCreateFxDealList() {
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

        when(fxDealService.saveFxDealList(fxDealList)).thenReturn(fxDealList);

        ResponseEntity<List<FxDeal>> response = fxDealController.createFxDealList(fxDealList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fxDealList, response.getBody());

        verify(fxDealService, times(1)).saveFxDealList(fxDealList);
    }

    @Test
    public void testGetFxDeal() {
        FxDeal fxDeal = new FxDeal();
        fxDeal.setDealUniqueId("123");
        fxDeal.setFromCurrencyIsoCode("USD");
        fxDeal.setToCurrencyIsoCode("EUR");
        fxDeal.setDealTimestamp(123456789L);
        fxDeal.setDealAmount(new BigDecimal("100.00"));

        when(fxDealService.findByDealUniqueId("123")).thenReturn(fxDeal);

        ResponseEntity<FxDeal> response = fxDealController.getFxDeal("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fxDeal, response.getBody());

        verify(fxDealService, times(1)).findByDealUniqueId("123");
    }

    @Test
    public void testGetFxDealNotFound() {
        when(fxDealService.findByDealUniqueId("123")).thenReturn(null);

        ResponseEntity<FxDeal> response = fxDealController.getFxDeal("123");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(fxDealService, times(1)).findByDealUniqueId("123");
    }

    @Test
    public void testGetFxDealList() {
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

        when(fxDealService.getAllFxDeals()).thenReturn(fxDealList);

        ResponseEntity<List<FxDeal>> response = fxDealController.getFxDealList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(fxDealList, response.getBody());

        verify(fxDealService, times(1)).getAllFxDeals();
    }
}

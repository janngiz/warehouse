package com.clusus.warehouse.controllers;


import com.clusus.warehouse.entities.FxDeal;
import com.clusus.warehouse.services.FxDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/fxdeals")
public class FxDealController {
    private final FxDealService fxDealService;

    @Autowired
    public FxDealController(FxDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping("/save")
    public ResponseEntity<FxDeal> createFxDeal(@RequestBody FxDeal fxDeal) {
        FxDeal savedFxDeal = fxDealService.saveFxDeal(fxDeal);
        return ResponseEntity.ok(savedFxDeal);
    }

    @GetMapping("/{dealUniqueId}")
    public ResponseEntity<FxDeal> getFxDeal(@PathVariable String dealUniqueId) {
        FxDeal fxDeal = fxDealService.findByDealUniqueId(dealUniqueId);
        if (fxDeal != null) {
            return ResponseEntity.ok(fxDeal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
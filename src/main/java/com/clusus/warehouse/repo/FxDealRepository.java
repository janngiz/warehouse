package com.clusus.warehouse.repo;

import com.clusus.warehouse.entities.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FxDealRepository extends JpaRepository<FxDeal, String> {
    FxDeal findByDealUniqueId(String dealUniqueId);
}

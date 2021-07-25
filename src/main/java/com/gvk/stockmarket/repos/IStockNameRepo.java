package com.gvk.stockmarket.repos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gvk.stockmarket.models.StockName;

public interface IStockNameRepo extends JpaRepository<StockName, Integer> {
	
	List<StockName> findByFavorite(Boolean favorite, Sort s);
	
}

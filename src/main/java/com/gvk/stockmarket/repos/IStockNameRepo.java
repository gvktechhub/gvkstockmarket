package com.gvk.stockmarket.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gvk.stockmarket.models.StockName;

public interface IStockNameRepo extends JpaRepository<StockName, Integer> {

}

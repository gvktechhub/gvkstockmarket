package com.gvk.stockmarket.services;

import java.util.List;

import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.models.Stocks;

public interface IStockService {
	
	List<Stocks> getAllStocksByStock(StockName stock, Integer quantity);

}

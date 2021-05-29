package com.gvk.stockmarket.services;

import java.util.List;

import com.gvk.stockmarket.models.StockActions;

public interface IStockActionsService {
	
	StockActions save(StockActions action);
	
	List<Object[]> getAvailableStocks();

}

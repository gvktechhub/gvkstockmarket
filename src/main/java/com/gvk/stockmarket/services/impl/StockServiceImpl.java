package com.gvk.stockmarket.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.models.Stocks;
import com.gvk.stockmarket.repos.IStocksRepo;
import com.gvk.stockmarket.services.IStockService;

@Service
public class StockServiceImpl implements IStockService {
	
	@Autowired
	private IStocksRepo stocksRepo;

	@Override
	public List<Stocks> getAllStocksByStock(StockName stock, Integer quantity) {
		return null;
	}

}

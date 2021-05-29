package com.gvk.stockmarket.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.repos.IStocksRepo;
import com.gvk.stockmarket.services.IStockReportsService;

@Service
public class StockReportsService implements IStockReportsService {

	@Autowired
	private IStocksRepo  stocksRepo;
	
	@Override
	public List<Object[]> getMoreThanOneYearStocks() {
		return stocksRepo.getStocksForMoreThanOneYear();
	}

}

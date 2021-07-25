package com.gvk.stockmarket.services.impl;

import java.util.List;
import java.util.stream.Collectors;

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

	@Override
	public List<Object[]> getUpcomingMoreThanOneYearStocks() {
		return stocksRepo.getStocksForUpcomingMoreThanOneYear();
	}

	@Override
	public List<Object[]> getTopProfitableStocks() {
		return stocksRepo.getTopProfitableStocks().stream().filter(record -> {
			return Double.parseDouble(record[6].toString()) > 0;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Object[]> getTopUnprofitableStocks() {
		return stocksRepo.getTopUnprofitableStocks().stream().filter(record -> {
			return Double.parseDouble(record[6].toString()) < 0;
		}).collect(Collectors.toList());
	}

}

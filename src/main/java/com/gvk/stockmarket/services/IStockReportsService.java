package com.gvk.stockmarket.services;

import java.util.List;

public interface IStockReportsService {
	
	List<Object[]> getMoreThanOneYearStocks();
	
	List<Object[]> getUpcomingMoreThanOneYearStocks();
	
	List<Object[]> getTopProfitableStocks();
	
	List<Object[]> getTopUnprofitableStocks();

}

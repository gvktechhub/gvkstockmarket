package com.gvk.stockmarket.services;

import java.util.List;
import java.util.Optional;

import com.gvk.stockmarket.models.StockName;

public interface IStockNameService {
	
	public StockName save(StockName stock);
	
	public List<StockName> list();
	
	public Optional<StockName> get(Integer id);
	
	public void delete(Integer id);
	
	public StockName updateCurrentPriceByStockName(Integer stockNameId, Double currentPrice);

}

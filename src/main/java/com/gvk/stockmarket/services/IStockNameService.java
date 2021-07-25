package com.gvk.stockmarket.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.gvk.stockmarket.models.StockName;

public interface IStockNameService {
	
	public StockName save(StockName stock);
	
	public List<StockName> list();
	
	public Optional<StockName> get(Integer id);
	
	public void delete(Integer id);
	
	public StockName updateCurrentPriceByStockName(Map<String, Object> map);
	
	public List<StockName> findByFavorite(Boolean favorite, Sort s);

}

package com.gvk.stockmarket.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.repos.IStockNameRepo;
import com.gvk.stockmarket.services.IStockNameService;

@Service
public class StockNameServiceImpl implements IStockNameService {
	
	@Autowired
	private IStockNameRepo repo;

	@Override
	public StockName save(StockName stock) {
		return repo.save(stock);
	}

	@Override
	public List<StockName> list() {
		return repo.findAll();
	}

	@Override
	public Optional<StockName> get(Integer id) {
		return repo.findById(id);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public StockName updateCurrentPriceByStockName(Map<String, Object> map) {
		Optional<StockName> stockNameObj = get(Integer.valueOf(map.get("stockNameId").toString()));
		StockName stockName = null;
		if(stockNameObj.isPresent()) {
			
			Double currentPrice = Double.valueOf(map.get("currentPrice").toString());
			Double avgprice = Double.valueOf(map.get("avgprice").toString());
			Integer quantity = Integer.valueOf(map.get("quantity").toString());
			Double investment = quantity * avgprice;
			
			stockName = stockNameObj.get();
			stockName.setCurrentPrice(currentPrice);
			stockName.setCurrentValue(quantity * currentPrice);
			stockName.setProfitOrLossPercent((stockName.getCurrentValue() - investment)*100/investment);
			save(stockName);
		}
		return stockName;
	}

	@Override
	public List<StockName> findByFavorite(Boolean favorite, Sort s) {
		return repo.findByFavorite(favorite, s);
	}
}

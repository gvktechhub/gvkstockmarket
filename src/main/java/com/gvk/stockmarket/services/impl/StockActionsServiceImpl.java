package com.gvk.stockmarket.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gvk.stockmarket.enums.StockActionTypes;
import com.gvk.stockmarket.models.StockActions;
import com.gvk.stockmarket.models.Stocks;
import com.gvk.stockmarket.repos.IStockActionsRepo;
import com.gvk.stockmarket.repos.IStocksRepo;
import com.gvk.stockmarket.services.IStockActionsService;

@Service
@Transactional
public class StockActionsServiceImpl implements IStockActionsService {
	
	@Autowired
	private IStockActionsRepo stockActionsRepo;
	
	@Autowired
	private IStocksRepo stocksRepo;

	@Override
	public StockActions save(StockActions action) {
		stockActionsRepo.save(action);
		if(action != null) {
			if(action.getActionType().equals(StockActionTypes.BUY)) {
				for(int i = 0; i < action.getQuantity(); ++i)
					stocksRepo.save(new Stocks(action.getStock(),
							action.getActionType().equals(StockActionTypes.BUY) ? action : null,
							action.getActionType().equals(StockActionTypes.SELL) ? action :null));
			}
			else {
				List<Stocks> sellObjects = stocksRepo.findAllById(
							stocksRepo.getStocksForSell(
									action.getStock().getId(), action.getQuantity()
							).stream().map(
									record -> Integer.valueOf(record[0].toString())
							).collect(Collectors.toList())
				);
				
				sellObjects.stream().forEach(record -> {
					record.setSell(action);
				});
				
				stocksRepo.saveAll(sellObjects);
			}
		}
		return action;
	}

	@Override
	public List<Object[]> getAvailableStocks() {
		return stockActionsRepo.getAvailableStocks();
	}

}

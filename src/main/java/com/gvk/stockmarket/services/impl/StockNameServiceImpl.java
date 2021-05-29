package com.gvk.stockmarket.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
}

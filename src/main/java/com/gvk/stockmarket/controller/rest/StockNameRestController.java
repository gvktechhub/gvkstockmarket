package com.gvk.stockmarket.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.services.IStockNameService;

@RestController
@RequestMapping(value="/apis/stock-names")
public class StockNameRestController {
	
	@Autowired
	private IStockNameService stockNameService;
	
	@PostMapping(value="/save")
	public ResponseEntity<StockName> save(@RequestBody StockName stock) {
		StockName stockName = stockNameService.save(stock);
		return ResponseEntity.ok(stockName);
	}
	
	@GetMapping(value="/get")
	public ResponseEntity<StockName> get(@RequestParam("id") Integer id) {
		return ResponseEntity.ok(stockNameService.get(id).get());
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		stockNameService.delete(id);
		return ResponseEntity.ok(new StringBuilder().append("Stock "+id+" is deleted.").toString());
	}

}

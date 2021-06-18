package com.gvk.stockmarket.controller.rest;

import java.util.Optional;

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

import com.gvk.stockmarket.exceptions.StockNameIdNotFoundException;
import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.services.IStockNameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/apis/stock-names")
@Api( description = "Stock Name - Rest Operations")
public class StockNameRestController {
	
	@Autowired
	private IStockNameService stockNameService;
	
	@PostMapping(value="/save")
	@ApiOperation("Save / Update Operation")
	public ResponseEntity<StockName> save(@RequestBody StockName stock) {
		StockName stockName = stockNameService.save(stock);
		return ResponseEntity.ok(stockName);
	}
	
	@GetMapping(value="/get")
	@ApiOperation("Get Operation")
	public ResponseEntity<StockName> get(@RequestParam(name = "id", required = false, defaultValue = "1") Integer id) {
		Optional<StockName> stockName = stockNameService.get(id);
		if(stockName.isPresent()) {
			return ResponseEntity.ok(stockName.get());
	    } else throw new StockNameIdNotFoundException(new StringBuilder()
		.append("Stock ( ").append(id).append(" ) is not found in database..!").toString());
	}
	
	@DeleteMapping(value="/delete/{id}")
	@ApiOperation("Delete Operation")
	public ResponseEntity<String> delete(@PathVariable(name = "id") Integer id) {
		stockNameService.delete(id);
		return ResponseEntity.ok(new StringBuilder().append("Stock").append(" ( ")
				.append(id).append(" ) is deleted.").toString());
	}

}

package com.gvk.stockmarket.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.services.IStockNameService;

@Component
public class InitialRunner implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(InitialRunner.class);
	
	@Autowired
	private IStockNameService stockService;

	@Override
	public void run(String... args) throws Exception {
		log.info("STARTED :: Initializing initial runner");
		if(stockService.list().isEmpty()) loadStocks();
		log.info("ENDED :: initial runner");
	}
	
	private void loadStocks() {
		log.info("STARTED :: loadStocks");
		stockService.save(new StockName("TCS", "TCS"));
		log.info("ENDED :: loadStocks");
	}

}

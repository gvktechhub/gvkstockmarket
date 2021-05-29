package com.gvk.stockmarket.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.gvk.stockmarket.controller.StockNameController;
import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.services.IStockNameService;
import com.gvk.stockmarket.services.impl.StockNameServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(value = StockNameController.class)
public class StockNamesTestCase {
	
	@Autowired
	private static IStockNameService stockNameService;
	
	@BeforeAll
	public static void beforeAll() {
		//stockNameService = new StockNameServiceImpl();
	}
	
	@BeforeEach
	public void before() {
		
	}
	
	@Test
	public void saveTest() {
		assertNotNull(stockNameService.save(new StockName("TEST NAME 2", "TEST SYBMBOL 2")));
	}
	
	@AfterEach
	public void after() {
		
	}
	
	@AfterAll
	public static void afterAll() {
		
	}
}

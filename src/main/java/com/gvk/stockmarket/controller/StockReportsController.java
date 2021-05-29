package com.gvk.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gvk.stockmarket.services.IStockReportsService;

@RequestMapping(value = "/stock-reports")
@Controller
public class StockReportsController {
	
	private static final String folder = "/reports/";
	
	@Autowired
	private IStockReportsService stockReportsService;
	
	@GetMapping(value = "/more-than-year")
	public String moreThanYear(Model model) {
		model.addAttribute("moreThanOneYearStocksList", stockReportsService.getMoreThanOneYearStocks());
		return folder+"more-than-year";
	}

}

package com.gvk.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gvk.stockmarket.enums.StockActionTypes;
import com.gvk.stockmarket.models.StockActions;
import com.gvk.stockmarket.services.IStockActionsService;
import com.gvk.stockmarket.services.IStockNameService;

@Controller
@RequestMapping(value = "/stock-actions")
public class StockActionsController {
	
	@Autowired 
	private IStockNameService stockNameService;
	
	@Autowired
	private IStockActionsService stockActionsService;
	
	private static String folder = "stockactions/";
	
	@GetMapping(value = "/")
	public String action(Model model) {
		model.addAttribute("stockActionTypes", StockActionTypes.values());
		model.addAttribute("stocksList", stockNameService.list());
		model.addAttribute("stock", new StockActions());
		return folder+"action-page";
	}
	
	@PostMapping(value="/save")
	public String save(@ModelAttribute("stock") StockActions stock) {
		stockActionsService.save(stock);
		return "redirect:/stock-actions/";
	}
	
	@GetMapping(value="/available-stocks")
	public String getAvailableStocks(Model model) {
		model.addAttribute("availableStocksList", stockActionsService.getAvailableStocks());
		return folder+"available-stocks";
	}

}

package com.gvk.stockmarket.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	private static String folder = "pages/stockactions/";
	
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
		List<Object[]> availableStocks = stockActionsService.getAvailableStocks();
		Double totalamount = 0.0;
		totalamount = availableStocks.stream().map(record -> {
			return Double.parseDouble(record[2].toString()) * Double.parseDouble(record[3].toString());
		}).collect(Collectors.toList()).stream().mapToDouble(Double::valueOf).sum();
		model.addAttribute("availableStocksList", availableStocks);
		model.addAttribute("totalamount", totalamount);
		return folder+"available-stocks";
	}

}

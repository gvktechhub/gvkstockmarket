package com.gvk.stockmarket.controller;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gvk.stockmarket.enums.ModelTypes;
import com.gvk.stockmarket.enums.PageTypes;
import com.gvk.stockmarket.exceptions.StockNameIdNotFoundException;
import com.gvk.stockmarket.models.StockName;
import com.gvk.stockmarket.services.IStockNameService;

@Controller
@RequestMapping(value = "/stock-names")
public class StockNameController {
	
	private static final Logger log = LoggerFactory.getLogger(StockNameController.class);
	
	private static final String folder = "/stockname/";
	
	@Autowired
	private IStockNameService stockNameService;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		setInModel(model, ModelTypes.NEW_OBJECT);
		return returnType(PageTypes.FORM);
	}

	@PostMapping(value = "/save")
	public String save(@Valid @ModelAttribute StockName stockname, Model model) {
		StockName stock = stockNameService.save(stockname);
		model.addAttribute("message", new StringBuilder()
				.append("Stock name ( ")
				.append(stock.getName())
				.append(" ) Saved..!"));
		setInModel(model, ModelTypes.NEW_OBJECT);
		return returnType(PageTypes.FORM);
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		setInModel(model, ModelTypes.LIST);
		return returnType(PageTypes.LIST);
	}
	
	@GetMapping(value="/delete")
	public String delete(@RequestParam("id") Integer id, Model model) {
		stockNameService.delete(id);
		model.addAttribute("message", new StringBuilder()
				.append("Stock ( ")
				.append(id)
				.append(" ) is deleted..!"));
		setInModel(model, ModelTypes.LIST);
		return returnType(PageTypes.LIST);
	}
	
	@GetMapping(value="/edit")
	public String edit(@RequestParam("id") Integer id, Model model) {
		Optional<StockName> stockNameObj = stockNameService.get(id);
		if(stockNameObj.isPresent()) {
			model.addAttribute("stockname", stockNameObj.get());
		}
		else throw new StockNameIdNotFoundException("Stock Name ( "+id+ " ) is not found.");
		return returnType(PageTypes.FORM);
	}
	
	private String returnType(PageTypes type) {
		switch(type.toString()) {
			case "FORM":
				return folder+"index";
			case "LIST":
				return folder+"list";
			default:
				return "error";
		}
	}
	
	private void setInModel(Model model, ModelTypes type) {
		switch(type.toString()) {
			case "NEW_OBJECT":
				model.addAttribute("stockname", new StockName());
				break;
			case "LIST":
				model.addAttribute("stockNamesList", stockNameService.list());
				break;
		}
	}

}

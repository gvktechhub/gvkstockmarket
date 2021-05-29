package com.gvk.stockmarket.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="stock_names")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class StockName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NonNull
	@Column(name = "name", nullable = false)
	@NotBlank
	private String name;
	
	@NonNull
	@Column(name = "symobl", nullable = false, unique = true)
	@NotBlank
	private String symbol;
}
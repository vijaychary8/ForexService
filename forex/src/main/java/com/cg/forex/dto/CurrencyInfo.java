package com.cg.forex.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data

@Entity
@Table(name="currency_table")
@JsonRootName("AdminInfo")
@JsonInclude (content = Include.NON_NULL)

public class CurrencyInfo {
	public CurrencyInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, unique = true)
	@JsonProperty
	private String currencyName;
	
	@Column(nullable = false)
	@JsonProperty
	private double value;

	public CurrencyInfo(int id, String currencyName, double value) {
		super();
		this.id = id;
		this.currencyName = currencyName;
		this.value = value;
	}
	

}

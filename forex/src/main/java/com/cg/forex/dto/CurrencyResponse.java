package com.cg.forex.dto;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)

public class CurrencyResponse {

	private boolean isError;
	private CurrencyInfo currency;
	private List<CurrencyInfo> currencyInfoList;
	private HashMap<String, Double> rate;
	private String message;

}

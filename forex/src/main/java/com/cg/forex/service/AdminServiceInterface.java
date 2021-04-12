package com.cg.forex.service;

import java.util.HashMap;
import java.util.List;

import com.cg.forex.dto.Admin;
import com.cg.forex.dto.CurrencyInfo;
import com.cg.forex.dto.Message;
public interface AdminServiceInterface  {
	Admin validateEmail(String email, String password);

	Admin serviceAddAdmin(Admin admin);

	CurrencyInfo serviceAddCurrency(CurrencyInfo currencyInfo);

	boolean updateCurrencyInfo(CurrencyInfo currencyInfo);

	String deleteCurrencyInfo(int id);

	List<CurrencyInfo> viewAllCurrencies();

CurrencyInfo viewCurrency(String currencyName);

HashMap<String, Double> getRate(String sourceCurr, String targetCurr);


}

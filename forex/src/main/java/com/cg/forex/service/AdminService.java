package com.cg.forex.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cg.forex.constants.FSConstants;
import com.cg.forex.dao.AdminDao;
import com.cg.forex.dao.CurrencyDao;
import com.cg.forex.dto.Admin;
import com.cg.forex.dto.CurrencyInfo;
import com.cg.forex.dto.CurrencyResponse;
import com.cg.forex.exceptions.CurrencyAlreadyExist;
import com.cg.forex.exceptions.CurrencyDoesNotExistsException;

@Service

public class AdminService implements AdminServiceInterface {

	@Autowired
	private AdminDao dao;

	@Autowired
	private CurrencyDao currencyDao;

	@Override
	public Admin validateEmail(String email, String password) {
		return dao.validateEmail(email, password);
	}

	@Override
	public Admin serviceAddAdmin(Admin admin) {
		return dao.save(admin);
	}

	@Override
	public CurrencyInfo serviceAddCurrency(CurrencyInfo currencyInfo) {
		int count=0;
		CurrencyResponse response = new CurrencyResponse();
		CurrencyInfo currency = null;

		try {
			currency = currencyDao.save(currencyInfo);
			if(currency==null) {
				count++;
			}
			else {
				return currency;

			}

		} catch (DataIntegrityViolationException  e) {
			if(count==1) {
				throw new CurrencyAlreadyExist(FSConstants.CURRENCY_DETAILS_ALREADY_FOUND);
			}
		}
		return null;
	}

	@Override
	public boolean updateCurrencyInfo(CurrencyInfo currencyInfo) {
		if (!currencyDao.existsById(currencyInfo.getId())) {
			return false;
		}
		currencyDao.save(currencyInfo);
		return true;
	}

	@Override
	public String deleteCurrencyInfo(int id) {

		if (!currencyDao.existsById(id)) {
			return FSConstants.CURRENCY_DETAILS_DOES_NOT_EXISTS;
		}
		currencyDao.deleteById(id);
		return FSConstants.CURRENCY_DETAILS_REMOVED_SUCCESSFULLY;
	}

	@Override
	public List<CurrencyInfo> viewAllCurrencies() throws CurrencyDoesNotExistsException {
		List<CurrencyInfo> currency = currencyDao.findAll();
		if (currency == null) {
			throw new CurrencyDoesNotExistsException(FSConstants.CURRENCY_DETAILS_DOES_NOT_EXISTS);

		} else {
			return currency;

		}
	}

	@Override
	public CurrencyInfo viewCurrency(String currencyName) {
		CurrencyInfo currency = currencyDao.findCurrency(currencyName);
		return currency;
	}

	@Override
	public HashMap<String, Double> getRate(String sourceCurr, String targetCurr) {
		HashMap<String, Double> rate = new HashMap<String, Double>();
		int count = 0;
		try {
			CurrencyInfo currency = currencyDao.findCurrency(sourceCurr);

			double sourceCurrencyRate = currency.getValue();
			CurrencyInfo currency1 = currencyDao.findCurrency(targetCurr);
			double targetCurrencyRate = currency1.getValue();
			if (sourceCurrencyRate == 0 && targetCurrencyRate == 0) {
				count++;
			} else {
				rate.put("sourceCurrencyRate", sourceCurrencyRate);
				rate.put("targetCurrencyRate", targetCurrencyRate);
				return rate;

			}
		} catch (NullPointerException e) {
			if (count == 1) {
				throw new CurrencyDoesNotExistsException(FSConstants.CURRENCY_DETAILS_DOES_NOT_EXISTS);
			}

		}
		return null;

	}

}

package com.cg.forex.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cg.forex.dao.AdminDao;
import com.cg.forex.dto.Admin;
import com.cg.forex.dto.CurrencyInfo;
import com.cg.forex.service.AdminService;
@CrossOrigin("*")

@SpringBootTest
class AdminControllerTest {

	@Autowired
	AdminDao adminDao;

	@Autowired
	AdminService adminService;

	@Test
	void testLogin() {
		Admin admin = new Admin(3, "vijay@gmail.com", "vijay", "Maunagala", "Vijachary8");
		assertEquals(admin, adminDao.validateEmail("vijay@gmail.com", "Vijachary8"));

	}

	@Test
	void testLogin2() {
		assertEquals(null, adminDao.validateEmail("vijayy@gmail.com", "Vijachary8"));

	}

//	@Test
//	void addCurrency() {
//		CurrencyInfo currencyInfo = new CurrencyInfo(14, "USD", 0.013639);
//
//		assertEquals(null, adminService.serviceAddCurrency(currencyInfo));
//	}

	@Test
	void deleteCurrency() {
		assertEquals("Currency Details does not exists", adminService.deleteCurrencyInfo(58));

		
		
	}
	@Test
	void updateCurrency() {
		CurrencyInfo currencyInfo = new CurrencyInfo(67, "USD", 0.013639);
		
		assertEquals(true, adminService.updateCurrencyInfo(currencyInfo));

		
	}
	
	@Test 
	void getCurrencyTest() {
		CurrencyInfo currencyInfo = new CurrencyInfo(67, "USD", 0.013637	);
		assertEquals(currencyInfo, adminService.viewCurrency("USD"));
	}

	@Test
	void getAllCurrencies() {
		
		assertNotNull(adminService.viewAllCurrencies());
	}
	@Test
	void getRate() {
		HashMap<String, Double> rate= new HashMap<String, Double>();
		rate.put("sourceCurrencyRate", 455.0);
		rate.put("targetCurrencyRate",0.013637);
		assertEquals(rate, adminService.getRate("CAD", "USD"));
	}
}

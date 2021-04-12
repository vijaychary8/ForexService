package com.cg.forex.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.forex.dto.CurrencyInfo;
@Repository
public interface CurrencyDao extends JpaRepository<CurrencyInfo, Integer> {
	@Query("select a from CurrencyInfo a where a.currencyName=?1")
	CurrencyInfo findCurrency(String currencyName);
	
	@Query("select a from CurrencyInfo a where a.currencyName=?1")
	boolean isCurrency(String currencyName);

}

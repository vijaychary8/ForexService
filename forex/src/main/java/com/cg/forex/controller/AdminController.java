package com.cg.forex.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.forex.constants.FSConstants;
import com.cg.forex.dto.Admin;
import com.cg.forex.dto.CurrencyInfo;
import com.cg.forex.dto.CurrencyResponse;
import com.cg.forex.dto.Message;
import com.cg.forex.exceptions.AdminNotFoundException;
import com.cg.forex.exceptions.CurrencyDoesNotExistsException;
import com.cg.forex.service.AdminServiceInterface;
@CrossOrigin("*")
@RestController
public class AdminController{
	@Autowired
	private AdminServiceInterface adminService;

	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/validate")
	public ResponseEntity validateEmail(@RequestParam("email") String email,@RequestParam("password") String password ) throws AdminNotFoundException{
	Admin admin= adminService.validateEmail(email,password);
	if(admin==null) {
	ResponseEntity<String> response= new ResponseEntity<String>("{\"message\":\"No User Found\"}",HttpStatus.BAD_REQUEST) ;
	return (ResponseEntity) response;
	}
	else {
	ResponseEntity<Admin> response= new ResponseEntity<Admin>(admin,HttpStatus.OK);
	return (ResponseEntity) response;

	 }

	
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/addAdmin", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity addAdmin(@RequestBody Admin admin) {
		Admin isAdded = adminService.serviceAddAdmin(admin);
		if (isAdded==null) {
			ResponseEntity<String> response= new ResponseEntity<String>("{\"message\":\"Record is not Added\"}",HttpStatus.BAD_REQUEST) ;
			return (ResponseEntity) response;
		} else {
			ResponseEntity<String> response= new ResponseEntity<String>("{\"message\":\"Admin is Added\"}",HttpStatus.OK) ;
			return (ResponseEntity) response;
		}
	}// End of addAdmin()
	
	@GetMapping(path="/vijay")
	public String vijay() {
		return "vijay";
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/addCurrency", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public CurrencyResponse addCurrency(@RequestBody CurrencyInfo currencyInfo) {
		CurrencyResponse response=new CurrencyResponse();
		CurrencyInfo isAdded = adminService.serviceAddCurrency(currencyInfo);
		
		if (isAdded==null) {
			response.setError(true);
			response.setMessage(FSConstants.CURRENCY_DETAILS_ALREADY_FOUND);

		} else {
			response.setError(false);
			response.setMessage(FSConstants.CURRENCY_DETAILS_ADDED_SUCCESSFULLY);

		}
		return response;
	}// End of addAdmin()

	@DeleteMapping("/deleteCurrency")
	public Message deleteCurrencyInfo(@RequestParam int id) throws CurrencyDoesNotExistsException {
		
	  Message message = new Message(adminService.deleteCurrencyInfo(id));
		return message;
	}

	@PutMapping(path="/updateCurrency",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })

	public CurrencyResponse updateCurrencyInfo(@RequestBody CurrencyInfo currencyInfo)  {
		
	  boolean isUpdated = adminService.updateCurrencyInfo(currencyInfo);
		CurrencyResponse currentResponse = new CurrencyResponse();


		if (isUpdated) {
			currentResponse.setError(false);
			currentResponse.setMessage(FSConstants.CURRENCY_DETAILS_UPDATED_SUCCESSFULLY);
		} else {
			currentResponse.setError(true);
			currentResponse.setMessage(FSConstants.CURRENCY_DETAILS_DOES_NOT_EXISTS);
		}
		return currentResponse;

	  
	}
	
	
	@GetMapping(path="/viewAllCurrencies", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public CurrencyResponse viewAllCurrencyInfo()   {
		
		List<CurrencyInfo> list =new ArrayList<CurrencyInfo>(adminService.viewAllCurrencies());
		CurrencyResponse currentResponse = new CurrencyResponse();
		
		if(list!=null) {
		currentResponse.setError(false);
		currentResponse.setCurrencyInfoList(list);
		currentResponse.setMessage(FSConstants.CURRENCY_DETAILS_FOUND);
		
		}
		return currentResponse;


	}
	
	@GetMapping("/viewCurrency")
	public CurrencyResponse viewCurrencyInfo(@RequestParam String currencyName)   {
		CurrencyResponse currencyResponse = new CurrencyResponse();
		 CurrencyInfo currency=adminService.viewCurrency(currencyName);

		if (currency != null) {
			currencyResponse.setError(false);
			currencyResponse.setMessage("Get the record");
			currencyResponse.setCurrency(currency);
		} else {
			currencyResponse.setError(true);
			currencyResponse.setMessage("Currency is not present");
			currencyResponse.setCurrency(currency);
		}
		return currencyResponse;
	}
		
	

	@GetMapping("/currency-rate/for/{sourceCurr}/and/{targetCurr}")
	public CurrencyResponse getRate(@PathVariable(name="sourceCurr") String sourceCurr,@PathVariable(name="targetCurr") String targetCurr) {
		CurrencyResponse currencyResponse = new CurrencyResponse();

		HashMap<String, Double> getRate= adminService.getRate(sourceCurr, targetCurr);
		if(getRate!=null) {
			
			currencyResponse.setError(false);
			currencyResponse.setMessage("Get the record");
			currencyResponse.setRate(getRate);
		}else {
			currencyResponse.setError(true);
			currencyResponse.setMessage("Record Not Found");
			currencyResponse.setRate(null);

		}
		
		return currencyResponse;
		
	}
}



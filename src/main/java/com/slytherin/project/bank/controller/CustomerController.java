package com.slytherin.project.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slytherin.project.bank.model.ModelInputCardDetails;
import com.slytherin.project.bank.model.ModelSavedCardDetails;
import com.slytherin.project.bank.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PostMapping("/bank-creditdetails")
	public Map<String, Object> cardinfo(@RequestBody ModelInputCardDetails modelInputCardDetails) {
		Map<String, Object> map = service.creditcarddetails(modelInputCardDetails);
		return map;
	}

	@PostMapping("/bank-unbilled-txn")
	public ResponseEntity<?> getUnbilledTrans(@RequestBody ModelInputCardDetails modelInputCardDetails) {
		Map<String, Object> map = service.getUnbilledTxn(modelInputCardDetails);
		return ResponseEntity.ok(map);
	}

	@PostMapping("/bank-billed-txn")
	public ResponseEntity<?> getBilledTrans(@RequestBody ModelInputCardDetails modelInputCardDetails) {
		Map<String, Object> map = service.getBilledTxn(modelInputCardDetails);
		return ResponseEntity.ok(map);
	}

	@PostMapping("/bank-retail-txn")
	public ResponseEntity<?> getRetailTransactions(@RequestBody ModelInputCardDetails modelInputCardDetails) {
		Map<String, Object> map = service.getRetailTxn(modelInputCardDetails);
		return ResponseEntity.ok(map);
	}
	
}
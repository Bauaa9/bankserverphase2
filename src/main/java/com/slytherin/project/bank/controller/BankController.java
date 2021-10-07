package com.slytherin.project.bank.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.slytherin.project.bank.model.CardDetails;
import com.slytherin.project.bank.model.FinalResult;
import com.slytherin.project.bank.model.OtpData;
import com.slytherin.project.bank.model.PaymentDetails;
import com.slytherin.project.bank.service.BankService;



@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class BankController {

	private static Logger LOG = LoggerFactory.getLogger(BankController.class);

	@Autowired
	BankService service;

	@PostMapping("/validate-card")
	public ResponseEntity<Map<String, String>> validateCardDetails(@RequestBody PaymentDetails paymentDetails) {

		try {
			CardDetails checkCard = service.getCardDetails(paymentDetails.getCardNumber());
			ResponseEntity<Map<String, String>> result = service.checkCardDetails(checkCard, paymentDetails);
			return result;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something Went wrong. Try again later", e);
		}
	}

	@GetMapping("/generate-otp")
	public boolean generateOtp(@RequestParam("txnId") String txnId, @RequestParam("cardnum") String cardnum) {
		try {
			int otp = service.generateOtp();
			service.sendMail(otp, cardnum);
			service.storeOTP(otp, Integer.parseInt(txnId));
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something Went wrong. Try again later", e);
		}
	}

	@PostMapping(path = "/validate-otp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FinalResult> validateOtp(@RequestBody OtpData otpData) {
		try {
			LOG.info("OTP data received");
			return service.verifyAndProcessPayment(otpData);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Something Went wrong. Try again later", e);
		}

	}

}
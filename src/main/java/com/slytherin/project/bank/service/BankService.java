package com.slytherin.project.bank.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.slytherin.project.bank.dao.CardRepository;
import com.slytherin.project.bank.dao.OTPStoreRepository;
import com.slytherin.project.bank.dao.TransRepository;
import com.slytherin.project.bank.model.CardDetails;
import com.slytherin.project.bank.model.FinalResult;
import com.slytherin.project.bank.model.OTPStore;
import com.slytherin.project.bank.model.OtpData;
import com.slytherin.project.bank.model.PaymentDetails;
import com.slytherin.project.bank.model.Transactions;




/** @Author Shreyas Purkar */

@Service
public class BankService {

	private static Logger LOG = LoggerFactory.getLogger(BankService.class);

	@Autowired
	CardRepository cardRepository;

	@Autowired
	TransRepository transRepository;

	@Autowired
	OTPStoreRepository otpRepository;

	@Autowired
	private Environment env;

	@Autowired
	JavaMailSender javaMailSender;

	public CardDetails getCardDetails(String string) throws Exception{
		return this.cardRepository.findCardDetails(string);
	}

	public int getTxnId() throws Exception{
		return transRepository.getTransId();

	}

	public ResponseEntity<Map<String, String>> checkCardDetails(CardDetails checkCard, PaymentDetails paymentDetails) throws Exception{
		ArrayList<String> err = new ArrayList<String>();
		boolean isAllDataValid = true;
		if (checkCard != null) {
			if (!checkCard.getCardType().equals(paymentDetails.getCardType())) {
				err.add("err_invalid_card_type");
				isAllDataValid = false;
			}
			if (!checkCard.getCardNumber().equals(paymentDetails.getCardNumber())) {
				err.add("err_invalid_card_number");
				isAllDataValid = false;
			}
			if (!checkCard.getCardHolderName().equals(paymentDetails.getHolderName())) {
				err.add("err_invalid_card_name");
				isAllDataValid = false;
			}
			if (!checkCard.getExpiryDate().equals(paymentDetails.getExpDate())) {
				err.add("err_invalid_card_date");
				isAllDataValid = false;
			}
			if (!checkCard.getCvv().equals(paymentDetails.getCvv())) {
				err.add("err_invalid_card_cvv");
				isAllDataValid = false;
			}

			if (isAllDataValid == true) {
				String generateOtpURL = env.getProperty("generateOtpUrl").toString();
				String url = generateOtpURL + "?txnId=" + getTxnId() + "&cardnum=" + paymentDetails.getCardNumber();
				System.out.println(url);

				RestTemplate template = new RestTemplate();
				boolean response = template.getForObject(url, boolean.class);

				if (response) {
					LOG.info("OTP generated");
				} else {
					LOG.info("OTP generatation failed");
				}

				Map<String, String> result = new HashMap<String, String>();
				result.put("status", "valid");
				result.put("txn_id", String.valueOf(getTxnId()));

				System.out.println(result);
				
				boolean txnCreationStatus = createTransaction(getTxnId(),checkCard,paymentDetails);
				
				if (txnCreationStatus) {
					LOG.info("Transaction created");
				} else {
					LOG.info("Tranaction creation failed");
				}
				
				return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
			} else {
				Map<String, String> result = new HashMap<String, String>();
				result.put("status", "invalid");
				result.put("error", err.toString());
				return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
			}
		} else {
			err.add("err_invalid_card_number");
			Map<String, String> result = new HashMap<String, String>();
			result.put("status", "invalid");
			result.put("error", err.toString());
			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);
		}
	}


	private boolean createTransaction(int txnId, CardDetails checkCard, PaymentDetails paymentDetails) throws Exception{
		
		Transactions newTransaction = new Transactions();
		int customerId = cardRepository.findCustomerById(checkCard.getId());
		
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String currentTime = time.format(formatter);
		
		newTransaction.setAmount(paymentDetails.getTotalAmt());
		newTransaction.setCard_id(checkCard.getId());
		newTransaction.setCustomerId(customerId);
		newTransaction.setDateTime(currentTime);
		newTransaction.setMerchantName(paymentDetails.getMerchantName());
		newTransaction.setPaymentMethod(paymentDetails.getPaymentMethod());
		newTransaction.setPgRefId(paymentDetails.getPgRefId());
		newTransaction.setStatus("Pending");
		newTransaction.setTrsId(txnId);
		
		if(transRepository.save(newTransaction)!=null) {
			return true;
		}
		
		return false;
	}

	public int generateOtp() {
		return (int) (Math.random() * 9000) + 1000;
	}

	/// check this
	public void sendMail(int otp, String cardnum) throws Exception{
		String email = cardRepository.findEmailId(cardnum);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("groupslytherin@gmail.com");
		message.setTo(email);
		message.setSubject("Native Flow One Time Password");
		message.setText("Welcome to Native Flow, your OTP for current transaction is: " + otp + " Thank you!");
		javaMailSender.send(message);
	}

	public void storeOTP(int otp, int txnId) throws Exception{
		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String currentTime = time.format(formatter);
		OTPStore otpStore = new OTPStore(txnId, otp, currentTime);
		otpRepository.save(otpStore);
	}

	public OTPStore getOTP(int txnId) throws Exception{
		return otpRepository.findById(txnId).get();
	}

	public ResponseEntity<FinalResult> verifyAndProcessPayment(OtpData otpData) throws Exception{
		String otpReceivedFromUser = otpData.getOtp();
		OTPStore otpDetails = getOTP(otpData.getTxnId());
		String otpInDb = String.valueOf(otpDetails.getOtp());

		String otpTimeInDb = otpDetails.getCurrentTime();
		Date otpTime = new SimpleDateFormat("HH:mm:ss").parse(otpTimeInDb);

		LocalTime time = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		String currentTime = time.format(formatter);
		Date currTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
		Long timeDiff = currTime.getTime() - otpTime.getTime();

		Transactions ongoingTransaction = new Transactions();
		ongoingTransaction = transRepository.findById(otpData.getTxnId()).get();
		
		FinalResult result = new FinalResult();
		result.setPgRefId(ongoingTransaction.getPgRefId());
		
		if (otpReceivedFromUser.equals(otpInDb) && (timeDiff < 300000)) {
			
			result.setStatus("completed");
			ongoingTransaction.setStatus("completed");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} else {
			
			result.setStatus("failed");
			ongoingTransaction.setStatus("failed");
			return ResponseEntity.status(HttpStatus.OK).body(result);
		}
	}

	
}

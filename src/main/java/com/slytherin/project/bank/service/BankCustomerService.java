package com.slytherin.project.bank.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slytherin.project.bank.dao.CarddetailsRepo;
import com.slytherin.project.bank.dao.CardlimitRepo;
import com.slytherin.project.bank.dao.TransactionDao;
import com.slytherin.project.bank.model.CardDetails;
import com.slytherin.project.bank.model.ModelCard;
import com.slytherin.project.bank.model.ModelCardlimit;
import com.slytherin.project.bank.model.ModelInputCardDetails;
import com.slytherin.project.bank.model.ModelTransaction;

@Service
public class BankCustomerService {

	@Autowired
	CarddetailsRepo repoCardDetails;

	@Autowired
	CardlimitRepo repoCardLimitDetails;

	@Autowired
	TransactionDao transactionDao;
	

	public Map<String, Object> creditcarddetails(ModelInputCardDetails modelInputCardDetails) throws Exception{
		
		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		System.out.println(obj.getId());
//		ModelCard modelCard = new ModelCard();
		UtilAES utilAES = new UtilAES();
//		obj.setCardNumber(modelCard.getCardNumber());
		
		
		obj.setCardNumber(utilAES.decrypt(obj.getCardNumber()));
		obj.setCardHolderName(utilAES.decrypt(obj.getCardHolderName()));
//		obj.setCardType(utilAES.decrypt(obj.getCardType()));
		obj.setCvv(utilAES.decrypt(obj.getCvv()));
		obj.setExpiryDate(utilAES.decrypt(obj.getExpiryDate()));
		
		ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		double totaloutstanding = Double.valueOf(obj1.getTotalcreditlimit())
				- Double.valueOf(obj1.getAvailablecreditlimit());
		map.put("cardetails", obj);
		map.put("cardlimit", obj1);
		map.put("totaloutstanding", totaloutstanding);
		return map;

	}

	public Map<String, Object> getUnbilledTxn(ModelInputCardDetails modelInputCardDetails) throws Exception{

		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getId());
		String nextStatementDate = transactionDao.findNextStmtDate(obj1.getLaststatementdate());
//		System.out.println(nextStatementDate);
		List<ModelTransaction> modelTransaction = transactionDao.findUnBilledTransactions(obj1.getLaststatementdate(),
				nextStatementDate,obj.getId());
		Map<String, Object> map = new HashMap<String, Object>();
//		Float totalOutstandingAmount = transactionDao.getTotalOutstandingAmount(obj1.getLaststatementdate(),
//				nextStatementDate);
		double totaloutstanding = Double.valueOf(obj1.getTotalcreditlimit())
				- Double.valueOf(obj1.getAvailablecreditlimit());
//		System.out.println(totaloutstanding );
		map.put("unbilledTxn", modelTransaction);
		map.put("totalOutstandingAmount",totaloutstanding  );
		return map;

	}
	
	public Map<String, Object> getBilledTxn(ModelInputCardDetails modelInputCardDetails) throws Exception{

		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getId());
		System.out.println(obj1.getLaststatementdate());
		String previousStatementDate = transactionDao.findPreviousStmtDate(obj1.getLaststatementdate());
		System.out.println(previousStatementDate);

		List<ModelTransaction> modelTransaction = transactionDao.findBilledTransactions(obj1.getLaststatementdate(),
				previousStatementDate);
		Map<String, Object> map = new HashMap<String, Object>();
		Float totalAmountDue = transactionDao.getTotalAmountDue(obj1.getLaststatementdate(), previousStatementDate);
		totalAmountDue = transactionDao.getTotalDeditAmount(obj1.getLaststatementdate(),previousStatementDate)-transactionDao.getTotalCreditAmount(obj1.getLaststatementdate(), previousStatementDate);
		map.put("billedTxn", modelTransaction);
		map.put("totalAmountDue", transactionDao.getTotalDeditAmount(obj1.getLaststatementdate(),previousStatementDate)-transactionDao.getTotalCreditAmount(obj1.getLaststatementdate(), previousStatementDate));
		map.put("minAmountDue", (int) (totalAmountDue / 10));
		return map;
	}

	public Map<String, Object> getRetailTxn(ModelInputCardDetails modelInputCardDetails) throws Exception{
		Map<String, Object> map;

		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		List<ModelTransaction> modelTransaction = transactionDao.findRetailTransactions();
		map = new HashMap<String, Object>();
		map.put("retailTxn", modelTransaction);
		return map;

	}

}
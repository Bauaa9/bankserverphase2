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
import com.slytherin.project.bank.model.ModelCardlimit;
import com.slytherin.project.bank.model.ModelInputCardDetails;
import com.slytherin.project.bank.model.ModelTransaction;

@Service
public class CustomerService {

	@Autowired
	CarddetailsRepo repoCardDetails;

	@Autowired
	CardlimitRepo repoCardLimitDetails;

	@Autowired
	TransactionDao transactionDao;
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	private static final String ALGORITHM = "AES";
	private String secretkey = "slytherinNew";
	
	public void prepareSecreteKey(String myKey) throws Exception {
		MessageDigest sha = null;

		key = myKey.getBytes(StandardCharsets.UTF_8);
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16);
		secretKey = new SecretKeySpec(key, ALGORITHM);

	}
	
	public String decrypt(String strToDecrypt, String secret) throws Exception {

		prepareSecreteKey(secret);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

	}

	public Map<String, Object> creditcarddetails(ModelInputCardDetails modelInputCardDetails) throws Exception{
		
		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		System.out.println(obj.getId());		
		obj.setCardNumber(decrypt(obj.getCardNumber(),secretkey));
		System.out.println("decrpyt"+obj.getCardNumber());
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
				nextStatementDate);
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
		List<ModelTransaction> modelTransaction = transactionDao.findBilledTransactions(obj1.getLaststatementdate(),
				previousStatementDate);
		Map<String, Object> map = new HashMap<String, Object>();
		Float totalAmountDue = transactionDao.getTotalAmountDue(obj1.getLaststatementdate(), previousStatementDate);
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
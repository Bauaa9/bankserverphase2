package com.slytherin.project.service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.slytherin.project.dao.CarddetailsRepo;
import com.slytherin.project.dao.CardlimitRepo;
import com.slytherin.project.dao.TransactionDao;
import com.slytherin.project.model.ModelCardlimit;
import com.slytherin.project.model.ModelInputCardDetails;
import com.slytherin.project.model.ModelSavedCardDetails;
import com.slytherin.project.model.ModelTransaction;


@Service
public class CustomerService {

	@Autowired
	CarddetailsRepo repoCardDetails;

	@Autowired
	CardlimitRepo repoCardLimitDetails;

	@Autowired
	TransactionDao transactionDao;
	
	public Map<String, Object> creditcarddetails(ModelInputCardDetails modelInputCardDetails) {
		try {
			ModelSavedCardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),modelInputCardDetails.getCardId());
			ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getCard_id());
			Map<String, Object> map = new HashMap<String, Object>();
			double totaloutstanding = Double.valueOf(obj1.getTotalcreditlimit())
					- Double.valueOf(obj1.getAvailablecreditlimit());
			map.put("cardetails", obj);
			map.put("cardlimit", obj1);
			map.put("totaloutstanding", totaloutstanding);
			return map;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NumberFormatException();
		}

	}

	public Map<String, Object> getUnbilledTxn(ModelInputCardDetails modelInputCardDetails) {
		try {
			ModelSavedCardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),modelInputCardDetails.getCardId());
			ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getCard_id());
			String nextStatementDate = transactionDao.findNextStmtDate(obj1.getLaststatementdate());
			System.out.println(nextStatementDate);
			List<ModelTransaction> modelTransaction = transactionDao.findUnBilledTransactions(obj1.getLaststatementdate(),
					nextStatementDate);
			Map<String, Object> map = new HashMap<String, Object>();
			Float totalOutstandingAmount = transactionDao.getTotalOutstandingAmount(obj1.getLaststatementdate(),
					nextStatementDate);
			System.out.println(totalOutstandingAmount);
			map.put("unbilledTxn", modelTransaction);
			map.put("totalOutstandingAmount", totalOutstandingAmount);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error is "+ e);
			
		}
		return null;
	}

	public Map<String, Object> getBilledTxn(ModelInputCardDetails modelInputCardDetails) {
		try {
			ModelSavedCardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),modelInputCardDetails.getCardId());
			ModelCardlimit obj1 = repoCardLimitDetails.findLimit(obj.getCard_id());
			System.out.println(obj1.getLaststatementdate());
			String previousStatementDate = transactionDao.findPreviousStmtDate(obj1.getLaststatementdate());
			List<ModelTransaction> modelTransaction = transactionDao.findBilledTransactions(obj1.getLaststatementdate(),
					previousStatementDate);
			Map<String, Object> map = new HashMap<String, Object>();
			Float totalAmountDue = transactionDao.getTotalAmountDue(obj1.getLaststatementdate(),
					previousStatementDate);
			map.put("billedTxn", modelTransaction);
			map.put("totalAmountDue", totalAmountDue);
			map.put("minAmountDue", (int)(totalAmountDue/9));
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error is"+ e);
		}
		return null;
	}
	
	
	public Map<String, Object> getRetailTxn(ModelInputCardDetails modelInputCardDetails) {
		Map<String, Object> map;
		try {
			ModelSavedCardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),modelInputCardDetails.getCardId());
			List<ModelTransaction> modelTransaction = transactionDao.findRetailTransactions();
			map = new HashMap<String, Object>();
			map.put("retailTxn", modelTransaction);
			return map;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
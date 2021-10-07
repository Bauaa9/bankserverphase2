package com.slytherin.project.bank.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public Map<String, Object> creditcarddetails(ModelInputCardDetails modelInputCardDetails) throws Exception{

		CardDetails obj = repoCardDetails.findCard(modelInputCardDetails.getUserId(),
				modelInputCardDetails.getCardId());
		System.out.println(obj.getId());
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
		map.put("totalAmountDue", totalAmountDue);
		map.put("minAmountDue", (int) (totalAmountDue / 9));
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
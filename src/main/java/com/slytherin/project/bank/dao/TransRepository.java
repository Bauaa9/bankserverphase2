package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.slytherin.project.bank.model.Transactions;


public interface TransRepository extends JpaRepository<Transactions, Integer> {
	
	@Query(value = "select max(trs_id)+1 from transactions", nativeQuery = true)
	public int getTransId();
	
	@Query(value = "select * from transactions where trs_id=?1", nativeQuery = true)
	public Transactions getTransaction(int txnID);

}

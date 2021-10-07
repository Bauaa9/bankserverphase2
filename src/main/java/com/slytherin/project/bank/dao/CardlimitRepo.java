package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slytherin.project.bank.model.ModelCardlimit;


@Repository
public interface CardlimitRepo extends JpaRepository<ModelCardlimit, Integer> {
	
	@Query(value="select * from card_limit_details where card_token=?1", nativeQuery=true)
	public ModelCardlimit findLimit(int id);

	@Query(value="select available_credit_limit from card_limit_details where card_token=?1", nativeQuery=true)
	public String getLimitAmount(int txnId);
	
	

}

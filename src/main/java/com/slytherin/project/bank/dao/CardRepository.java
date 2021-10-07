package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.slytherin.project.bank.model.CardDetails;


public interface CardRepository extends JpaRepository<CardDetails, Integer> {

	@Query(value = "select * from card_details where card_number=?1", nativeQuery = true)
	public CardDetails findCardDetails(String string);

	@Query(value = "SELECT email  FROM customerdetails inner join card_details WHERE card_details.card_number=?1", nativeQuery = true)
	public String findEmailId(String cardnum);
	
	@Query(value = "SELECT customer_detail_id  FROM customerdetails inner join card_details WHERE card_details.card_id=?1", nativeQuery = true)
	public int findCustomerById(int id);

}

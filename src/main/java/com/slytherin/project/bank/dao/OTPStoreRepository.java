package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slytherin.project.bank.model.OTPStore;



/** @Author Shreyas Purkar */
public interface OTPStoreRepository extends JpaRepository<OTPStore, Integer> {

	@Query(value = "insert into otp_store values(?1,?2,?3)",nativeQuery = true)
	public void insertIntoOTP(int txn_id,int otp,String dateTime);
}

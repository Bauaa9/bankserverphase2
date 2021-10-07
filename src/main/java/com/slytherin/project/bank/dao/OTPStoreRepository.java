package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slytherin.project.bank.model.OTPStore;



/** @Author Shreyas Purkar */

public interface OTPStoreRepository extends JpaRepository<OTPStore, Integer> {

}

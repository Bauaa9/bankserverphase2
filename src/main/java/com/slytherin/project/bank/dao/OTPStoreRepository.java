package com.slytherin.project.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slytherin.project.bank.model.OTPStore;



/** @Author Shreyas Purkar */
@Repository
public interface OTPStoreRepository extends JpaRepository<OTPStore, Integer> {

}
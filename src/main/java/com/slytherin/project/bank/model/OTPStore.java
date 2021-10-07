package com.slytherin.project.bank.model;

/** @Author Shreyas Purkar */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp_store")
public class OTPStore {
	
	@Id
	@Column(name = "txn_id")
	int txnId;
	@Column(name = "otp")
	int otp;
	@Column(name = "current_time")
	String currentTime;
	
	public OTPStore() {
		
	}

	public OTPStore(int txnId, int otp, String currentTime) {
		super();
		this.txnId = txnId;
		this.otp = otp;
		this.currentTime = currentTime;
	}

	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "OTPStore [txn_id=" + txnId + ", otp=" + otp + ", currentTime=" + currentTime + "]";
	}
	
	
}

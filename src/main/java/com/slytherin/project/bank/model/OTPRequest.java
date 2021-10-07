package com.slytherin.project.bank.model;

public class OTPRequest {
	
	int txnId;
	String cardNumber;
	
	public OTPRequest() {
		// TODO Auto-generated constructor stub
	}

	public OTPRequest(int txnId, String cardNumber) {
		super();
		this.txnId = txnId;
		this.cardNumber = cardNumber;
	}

	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	

}

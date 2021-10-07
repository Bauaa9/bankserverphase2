package com.slytherin.project.bank.model;

public class GenerateOTP {

	String txnID;
	String cardNum;
	
	public GenerateOTP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerateOTP(String txnID, String cardNum) {
		super();
		this.txnID = txnID;
		this.cardNum = cardNum;
	}

	public String getTxnID() {
		return txnID;
	}

	public void setTxnID(String txnID) {
		this.txnID = txnID;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}

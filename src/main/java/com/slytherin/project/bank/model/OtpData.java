package com.slytherin.project.bank.model;

public class OtpData {
	int txnId;
	String otp;
	float totalAmt;
	int cardIdToPay;
	
	
	public int getCardIdToPay() {
		return cardIdToPay;
	}
	public void setCardIdToPay(int cardIdToPay) {
		this.cardIdToPay = cardIdToPay;
	}
	public OtpData() {
		// TODO Auto-generated constructor stub
	}
	public OtpData(int txnId, String otp, float totalAmt,int cardIdToPay) {
		super();
		this.txnId = txnId;
		this.otp = otp;
		this.totalAmt = totalAmt;
		this.cardIdToPay=cardIdToPay;
	}
	public int getTxnId() {
		return txnId;
	}
	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public float getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}
	@Override
	public String toString() {
		return "OtpData [txnId=" + txnId + ", otp=" + otp + ", totalAmt=" + totalAmt + "]";
	}
		

}

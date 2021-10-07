package com.slytherin.project.bank.model;

public class OtpData {
	int txnId;
	String otp;
	float totalAmt;
	public OtpData() {
		// TODO Auto-generated constructor stub
	}
	public OtpData(int txnId, String otp, float totalAmt) {
		super();
		this.txnId = txnId;
		this.otp = otp;
		this.totalAmt = totalAmt;
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

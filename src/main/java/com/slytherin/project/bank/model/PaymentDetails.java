package com.slytherin.project.bank.model;

public class PaymentDetails {

	String cardType;
	String cardNumber;
	String cvv;
	String holderName;
	String expDate;
	Float totalAmt;
	String pgRefId;
	String merchantName;
	String paymentMethod;
	
	public PaymentDetails()
	{
		
	}
	
	public PaymentDetails(String cardType, String cardNumber, String cvv, String holderName, String expDate,
			Float totalAmt, String pgRefId, String merchantName, String paymentMethod) {
		super();
		this.cardType = cardType;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.holderName = holderName;
		this.expDate = expDate;
		this.totalAmt = totalAmt;
		this.pgRefId = pgRefId;
		this.merchantName = merchantName;
		this.paymentMethod = paymentMethod;
	}



	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public Float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Float totalAmt) {
		this.totalAmt = totalAmt;
	}

	
	public String getPgRefId() {
		return pgRefId;
	}

	public void setPgRefId(String pgRefId) {
		this.pgRefId = pgRefId;
	}

	public String getMerchantName() {
		return merchantName;
	}



	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "Card [cardType=" + cardType + ", cardNumber=" + cardNumber + ", cvv=" + cvv + ", holderName="
				+ holderName + ", expDate=" + expDate + ", totalAmt=" + totalAmt + ", txn_id=" + pgRefId + "]";
	}

	

}

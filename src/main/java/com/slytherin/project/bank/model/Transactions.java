package com.slytherin.project.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transactions {

	@Id
	@Column(name="trs_id")
	int trsId;
	@Column(name="cus_id")
	int customerId;
	@Column(name="ca_id")
	int card_id;
	@Column(name="pg_ref_id")
	String pgRefId;
	@Column(name="status")
	String status;
	@Column(name="date_time")
	String dateTime;
	@Column(name="amount")
	Float amount;
	@Column(name="payment_method")
	String paymentMethod;
	@Column(name="merchant_name")
	String merchantName;
	
	public Transactions() {
		// TODO Auto-generated constructor stub
	}

	public Transactions(int trsId, int customerId, int card_id, String pgRefId, String status, String dateTime,
			Float amount, String paymentMethod, String merchantName) {
		super();
		this.trsId = trsId;
		this.customerId = customerId;
		this.card_id = card_id;
		this.pgRefId = pgRefId;
		this.status = status;
		this.dateTime = dateTime;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.merchantName = merchantName;
	}

	public int getTrsId() {
		return trsId;
	}

	public void setTrsId(int trsId) {
		this.trsId = trsId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getPgRefId() {
		return pgRefId;
	}

	public void setPgRefId(String pgRefId) {
		this.pgRefId = pgRefId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	
	
	
}

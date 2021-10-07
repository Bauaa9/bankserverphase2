package com.slytherin.project.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/** @Author Shreyas Purkar */

@Entity(name = "carddetails")
public class CardDetails {

	@Id
	@Column(name = "card_id")
	int id;
	@Column(name = "card_number")
	String cardNumber;
	@Column(name = "card_holdere_name")
	String cardHolderName;
	@Column(name = "cvv")
	String cvv;
	@Column(name = "expiry_date")
	String expiryDate;
	@Column(name = "card_type")
	String cardType;

	public CardDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CardDetails(int id, String cardNumber, String cardHolderName, String cvv, String expiryDate,
			String cardType) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.cardHolderName = cardHolderName;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.cardType = cardType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "CardDetails [id=" + id + ", cardNumber=" + cardNumber + ", cardHolderName=" + cardHolderName + ", cvv="
				+ cvv + ", expiryDate=" + expiryDate + ", cardType=" + cardType +"]";
	}

}

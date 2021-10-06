package com.slytherin.project.bank.model;

public class ModelInputCardDetails {
	
	int cardId;
	int userId;
	public ModelInputCardDetails(int cardId, int userId) {
		super();
		this.cardId = cardId;
		this.userId = userId;
	}
	public ModelInputCardDetails() {
		super();
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}

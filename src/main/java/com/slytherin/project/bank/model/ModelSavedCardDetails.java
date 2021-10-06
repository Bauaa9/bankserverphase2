package com.slytherin.project.bank.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="card_details")
public class ModelSavedCardDetails {
	
	@Id
	@Column(name="card_token")
	int card_id;
	@Column(name="customer_details_id")
	int customer_id;
	@Column(name="card_number")
	String card_number;
	@Column(name="cvv")
	String cvv;
	@Column(name="expiry_date")
	String expiry_date;
	@Column(name="card_type")
	String card_type;
	@Column(name="card_holder_name")
	String card_holder_name;
	
	public ModelSavedCardDetails()
	{
		
	}

	public ModelSavedCardDetails(int card_id, int customer_id, String card_number, String cvv, String expiry_date,
			String card_type, String card_holder_name) {
		super();
		this.card_id = card_id;
		this.customer_id = customer_id;
		this.card_number = card_number;
		this.cvv = cvv;
		this.expiry_date = expiry_date;
		this.card_type = card_type;
		this.card_holder_name = card_holder_name;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_holder_name() {
		return card_holder_name;
	}

	public void setCard_holder_name(String card_holder_name) {
		this.card_holder_name = card_holder_name;
	}

	@Override
	public String toString() {
		return "ModelCardDetails [card_id=" + card_id + ", customer_id=" + customer_id + ", card_number=" + card_number
				+ ", cvv=" + cvv + ", expiry_date=" + expiry_date + ", card_type=" + card_type + ", card_holder_name="
				+ card_holder_name + ", getCard_id()=" + getCard_id() + ", getCustomer_id()=" + getCustomer_id()
				+ ", getCard_number()=" + getCard_number() + ", getCvv()=" + getCvv() + ", getExpiry_date()="
				+ getExpiry_date() + ", getCard_type()=" + getCard_type() + ", getCard_holder_name()="
				+ getCard_holder_name() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}

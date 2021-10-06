package com.slytherin.project.bank.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="card_limit_details")
public class ModelCardlimit {
	@Id
	@Column(name="card_limit_id")
	int card_limit_id;
	@Column(name="card_token")
	int card_id;
	@Column(name="available_credit_limit")
	String availablecreditlimit;
	@Column(name="total_credit_limit")
	String totalcreditlimit;
	@Column(name="reward_points")
	String rewardpoints;
	@Column(name="last_statement_date")
	String laststatementdate;
	@Column(name="payment_due_date")
	String paymentduedate;
	@Column(name="total_cash_limit")
	String totalcashlimit;
	@Column(name="available_cash_limit")
	String availablecashlimit;
	
	public ModelCardlimit()
	{
		
	}

	public ModelCardlimit(int card_limit_id, int card_id, String availablecreditlimit, String totalcreditlimit,
			String rewardpoints, String laststatementdate, String paymentduedate, String totalcashlimit,
			String availablecashlimit) {
		super();
		this.card_limit_id = card_limit_id;
		this.card_id = card_id;
		this.availablecreditlimit = availablecreditlimit;
		this.totalcreditlimit = totalcreditlimit;
		this.rewardpoints = rewardpoints;
		this.laststatementdate = laststatementdate;
		this.paymentduedate = paymentduedate;
		this.totalcashlimit = totalcashlimit;
		this.availablecashlimit = availablecashlimit;
	}

	public int getCard_limit_id() {
		return card_limit_id;
	}

	public void setCard_limit_id(int card_limit_id) {
		this.card_limit_id = card_limit_id;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public String getAvailablecreditlimit() {
		return availablecreditlimit;
	}

	public void setAvailablecreditlimit(String availablecreditlimit) {
		this.availablecreditlimit = availablecreditlimit;
	}

	public String getTotalcreditlimit() {
		return totalcreditlimit;
	}

	public void setTotalcreditlimit(String totalcreditlimit) {
		this.totalcreditlimit = totalcreditlimit;
	}

	public String getRewardpoints() {
		return rewardpoints;
	}

	public void setRewardpoints(String rewardpoints) {
		this.rewardpoints = rewardpoints;
	}

	public String getLaststatementdate() {
		return laststatementdate;
	}

	public void setLaststatementdate(String laststatementdate) {
		this.laststatementdate = laststatementdate;
	}

	public String getPaymentduedate() {
		return paymentduedate;
	}

	public void setPaymentduedate(String paymentduedate) {
		this.paymentduedate = paymentduedate;
	}

	public String getTotalcashlimit() {
		return totalcashlimit;
	}

	public void setTotalcashlimit(String totalcashlimit) {
		this.totalcashlimit = totalcashlimit;
	}

	public String getAvailablecashlimit() {
		return availablecashlimit;
	}

	public void setAvailablecashlimit(String availablecashlimit) {
		this.availablecashlimit = availablecashlimit;
	}
	
	

}

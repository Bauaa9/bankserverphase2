package com.slytherin.project.bank.model;

public class FinalResult {
	
	String pgRefId;
	String status;
	
	public FinalResult() {
		// TODO Auto-generated constructor stub
	}

	public FinalResult(String pgRefId, String status) {
		super();
		this.pgRefId = pgRefId;
		this.status = status;
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
	
	

}

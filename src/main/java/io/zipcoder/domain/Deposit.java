package io.zipcoder.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deposit {
	
	public enum Type implements Serializable{
		P2P, DEPOSIT, WITHDRAWAL
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DEPOSIT_ID")
	private long id;
	
	@Column(name = "DEPOSIT_STATUS")
	private String status;
	
	public enum status implements Serializable{
		PENDING, CANCELLED, COMPLETED, RECURRING
	}
	
	@Column(name = "DEPOSIT_TRANSACTION_DATE")
	private String transaction_date;
	
	@Column(name = "DEPOSIT_TYPE")
	private String type;
	

	@Column(name = "DEPOSIT_PAYEE_ID") //account id
	private long payee_id;
	
	public void setId(long id) {
		this.id = id;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public enum Medium {
		BALANCE, REWARDS
	}
	
	@Column(name = "DEPOSIT_MEDIUM")
	private String medium;

	@Column(name = "DEPOSIT_AMOUNT")
	private double amount;
	
	@Column(name = "DEPOSIT_DESCRIPTION")
	private String description;
	
	
	
	public String getMedium() {
		return medium;
	}

	public void setMedium(Medium medium) {
		this.medium = medium.toString();
	}
	
	public String getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type.toString();
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus() {
		this.status = status;
	}
	
	public long getId() {
		return id;
	}

	public void setPayee_id(long payee_id) {
		this.payee_id = payee_id;
	}

	public long getPayee_id() {
		return payee_id;
	}

	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}

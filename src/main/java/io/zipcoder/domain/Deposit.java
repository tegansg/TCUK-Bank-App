package io.zipcoder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deposit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name = "DEPOSIT_ID")
	private long id;

	private enum Type{
		P2P, DEPOSIT, WITHDRAWAL
	}
	
	private enum Status{
		PENDING, CANCELLED, COMPLETED
	};
	
	@Column(name = "DEPOSIT_PAYEE_ID")
	private long payee_id;
	
	private enum Medium{
		BALANCE, REWARDS
	};
	
	@Column(name = "DEPOSIT_AMOUNT")
	private double amount;
	
	@Column(name = "DEPOSIT_DESCRIPTION")
	private String description;
	
	
	public long getId() {
		return id;
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

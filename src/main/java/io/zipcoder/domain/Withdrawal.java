package io.zipcoder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Withdrawal {
	@Id
	@GeneratedValue
	@Column(name = "WITHDRAWAL_ID")
	private long id;

	@Column(name = "TRANSACTION_TYPE")
	private String type;

	@Column(name = "TRANSACTION_DATE")
	private String transaction_date;

	@Column(name = "WITHDRAWAL_STATUS")
	private String status;

	@Column(name = "PAYER_ID") //account id
	private long payer_id;

	@Column(name = "WITHDRAWAL_TYPE")
	private String medium;

	@Column(name = "WITHDRAWAL_AMOUNT")
	private double amount;

	@Column(name = "WITHDRAWAL_DESCRIPTION")
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(long payer_id) {
		this.payer_id = payer_id;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getId() {
		return id;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public String getDescription() {
		return description;
	}

}

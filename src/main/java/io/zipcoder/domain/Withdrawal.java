package io.zipcoder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Withdrawal {
	@Id
	@GeneratedValue
	@Column(name = "Withdrawal_ID")
	private long id;
	@Column(name = "Transaction_Type")

	private String type;
	@Column(name = "Withdrawal_transaction_date")

	private String transaction_date;
	@Column(name = "Withdrawal_status")

	private String status;
	@Column(name = "Payer_ID")

	private long payer_id;
	@Column(name = "Withdrawal_type")

	private String medium;
	@Column(name = "Withdrawal_amount")

	private double amount;
	@Column(name = "Withdrawal_description")

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

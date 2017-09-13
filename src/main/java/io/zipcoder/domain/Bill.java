package io.zipcoder.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO: 
// 	- add in error. see below
// - and create InvalidDateException class
// and change RuntimeException

@Entity
public class Bill {
	
	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BILL_ID")
	private Long id;
	
	@Column(name = "STATUS")
	String status;
	public enum status implements Serializable{
		PENDING, CANCELLED, COMPLETED, RECURRING
	}
	@Column(name = "PAYEE")
	String payee;
	
	@Column(name = "NICKNAME")
	String nickname;
	
	@Column(name = "CREATION_DATE")
	String creation_date;
	
	@Column(name = "PAYMENT_DATE")
	String payment_date;
	
	@Column(name = "RECURRING_DATE")
	Integer recurring_date;
	
	@Column(name = "UPCOMING_DATE_PAYMENT")
	String upcoming_date_payment;
	
	@Column(name = "PAYMENT_AMOUNT")
	double payment_amount;
	
	@Column(name = "ACCOUNT_ID")
	Long account_id;
	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus() {
		this.status = status;
	}
	
	public String getPayee() {
		return payee;
	}
	
	public void setPayee(String payee) {
		this.payee = payee;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getCreation_date() {
		return creation_date;
	}
	
	public void setCreation_date(String creation_date) {
		checkValidDate(creation_date);
		this.creation_date = creation_date;
	}
	
	public void checkValidDate(String date) throws RuntimeException{
		if(!date.matches("\\d{2}\\-\\d{2}\\-\\d{4}")){
			throw new RuntimeException(); 
			//TODO: make InvalidDateException class in exception package
		}
	}
	
	public String getPayment_date() {
		return payment_date;
	}
	
	public void setPayment_date(String payment_date) {
		checkValidDate(payment_date);
		this.payment_date = payment_date;
		
	}
	public Integer getRecurring_date() { 
		return recurring_date;
	}
	public void setRecurring_date(Integer recurring_date) {
		if(recurring_date <= 31 && recurring_date > 0){
			this.recurring_date = recurring_date;
		}
//		TODO: else {
//			error
//		}
		
	}
	public String getUpcoming_date_payment() {
		return upcoming_date_payment;
	}
	
	public void setUpcoming_date_payment(String upcoming_date_payment) {
		checkValidDate(upcoming_date_payment);
		this.upcoming_date_payment = upcoming_date_payment;
	}
	
	public double getPayment_amount() {
		return payment_amount;
	}
	
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}
	
	public Long getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	
	public Long getId() {
		return id;
	}
	

}

package io.zipcoder.domain;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(name = "BILL_ID")
	private Long id;
	
	String status;
	String payee;
	String nickname;
	String creation_date;
	String payment_date;
	Integer recurring_date;
	String upcoming_date_payment;
	double payment_amount;
	String account_id;
	
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status.toString();
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
	
	public String getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public Long getId() {
		return id;
	}
	

}

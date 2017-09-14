package io.zipcoder.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Account {
	// when serialized should print out as a string
	public enum Type implements Serializable{
		SAVINGS, CHECKING, CREDIT;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCOUNT_ID")
	private long id;

	@Column(name = "ACCOUNT_TYPE")
	public Type type;

	@Column(name = "ACCOUNT_NICKNAME")
	private String nickname;

	@Column(name = "ACCOUNT_REWARDS")
	private int rewards;

	@Column(name = "ACCOUNT_BALANCE")
	private double balance;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;



	public String getNickname() {
		return nickname;
	}

	public Type getType() {
		return type;
	}

	// prints e.g. Leon's savings account
	public void setNickname(String nickname) {
		this.nickname = nickname + "'s" + this.getType() + "account";
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	public double getBalance() {
		return balance;
	}
	
	public boolean increaseBalance(double amount){
		
		if(amount >= 0.0){
			Double newBalance = getBalance() + amount;
			this.balance = newBalance;
			return true;
		}
		return false;

	}
	
	public boolean decreaseBalance(double amount){
		
		if(amount >= 0.0){
			Double newBalance = getBalance() - amount;
			this.balance = newBalance;
			return true;
		}
		return false;

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;

	}	public long getId() {
		return id;
	}

}

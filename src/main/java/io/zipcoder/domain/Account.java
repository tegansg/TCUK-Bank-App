package io.zipcoder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
	// when serialized should print out as a string
	public enum Type {
		SAVINGS, CHECKING, CREDIT;
	}

	@Column(name = "Account_type")

	public Type type;

	@Column(name = "Account_nickname")

	private String nickname;

	@Column(name = "Account_rewards")

	private int rewards;

	@Column(name = "Account_balance")

	private double balance;
	@Column(name = "Account_customer")

	private Customer customer;

	@Id
	@GeneratedValue
	// generates random ID
	private long id;

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

	public int getRewards() {
		return rewards;
	}

	public void setRewards(int rewards) {
		this.rewards = rewards;
	}

	public double getBalance() {
		return balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public long getId() {
		return id;
	}

}

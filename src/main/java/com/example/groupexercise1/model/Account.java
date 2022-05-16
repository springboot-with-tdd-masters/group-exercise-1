package com.example.groupexercise1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
	@Type(value = RegularAccount.class, name = "regular"),
	@Type(value = CheckingAccount.class, name = "checking"),
	@Type(value = InterestAccount.class, name = "interest")
})
public abstract class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String acctNumber;
	private Double balance;
	private Double minimumBalance;
	private Double penalty;
	private Double transactionCharge;
	private Double interestCharge;
	private Double amount;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAcctNumber() {
		return acctNumber;
	}
	
	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Double getMinimumBalance() {
		return minimumBalance;
	}
	
	public void setMinimumBalance(Double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}
	
	public Double getPenalty() {
		return penalty;
	}
	
	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}
	
	public Double getTransactionCharge() {
		return transactionCharge;
	}
	
	public void setTransactionCharge(Double transactionCharge) {
		this.transactionCharge = transactionCharge;
	}
	
	public Double getInterestCharge() {
		return interestCharge;
	}
	
	public void setInterestCharge(Double interestCharge) {
		this.interestCharge = interestCharge;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}

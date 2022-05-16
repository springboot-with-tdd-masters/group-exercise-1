package com.advancejava.groupexercise1.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("checking")
public class CheckingAccount extends Account{

    public CheckingAccount(String name) {
        super(name);
        this.setBalance(100.00);
        this.setMinimumBalance(100.00);
        this.setPenalty(10.00);
        this.setTransactionCharge(1.00);
        this.setInterestCharge(0.00);
        this.setType("checking");
    }
}

package com.advancejava.groupexercise1.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("interest")
public class InterestAccount extends Account{

    public InterestAccount(String name) {
        super(name);
        this.setBalance(0.00);
        this.setMinimumBalance(0.00);
        this.setPenalty(0.00);
        this.setTransactionCharge(0.00);
        this.setInterestCharge(0.03);
        this.setType("interest");
    }
}

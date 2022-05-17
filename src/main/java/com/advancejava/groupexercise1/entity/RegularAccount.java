package com.advancejava.groupexercise1.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("regular")
public class RegularAccount extends Account {
    public RegularAccount(String name) {
        super(name);
        this.setBalance(500.00);
        this.setMinimumBalance(500.00);
        this.setPenalty(10.00);
        this.setTransactionCharge(0.00);
        this.setInterestCharge(0.00);
    }
}

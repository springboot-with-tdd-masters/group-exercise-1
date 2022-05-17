package com.advancejava.groupexercise1.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Random;

@Entity
@JsonTypeName("checking")
public class CheckingAccount extends Account{

    public CheckingAccount() {
        Random random = new Random();
        //System.out.println();
        this.setAcctNumber(String.valueOf(random.nextInt(1000000)));
        this.setBalance(100.00);
        this.setMinimumBalance(100.00);
        this.setPenalty(10.00);
        this.setTransactionCharge(1.00);
        this.setInterestCharge(0.00);

    }
}

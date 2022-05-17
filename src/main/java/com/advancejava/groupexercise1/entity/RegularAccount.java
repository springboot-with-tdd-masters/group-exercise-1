package com.advancejava.groupexercise1.entity;


import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Random;

@Entity
@JsonTypeName("regular")
public class RegularAccount extends Account {

    private Double loan;

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    /**
     * Regular Account
     *
     * No interest
     *
     * Minimum/starting balance 500.00
     *
     * Penalty of 10.00 if balance falls below minimum
     */
    public RegularAccount() {
        /*super("1234567",  500.00, 500.00, 10.00, 0.00, 0.03);
        using this will force you to pass param and will cause to write @JsonProperty for each attributes/fields
         */
        Random random = new Random();
        //System.out.println();
        this.loan = 1000.00;//sample additional field specific for the child
        this.setAcctNumber(String.valueOf(random.nextInt(1000000)));
        this.setBalance(500.00);
        this.setMinimumBalance(500.00);
        this.setPenalty(10.00);
        this.setTransactionCharge(0.00);
        this.setInterestCharge(0.00);

    }
}

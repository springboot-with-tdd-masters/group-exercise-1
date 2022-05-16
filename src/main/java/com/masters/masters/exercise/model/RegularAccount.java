package com.masters.masters.exercise.model;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends Account{
    public RegularAccount() {
        super();
        this.setMinimumBalance(500);
        this.setPenalty(10);
        this.setBalance(500);
    }

}

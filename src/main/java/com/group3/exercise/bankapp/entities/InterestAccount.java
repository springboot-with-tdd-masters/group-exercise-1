package com.group3.exercise.bankapp.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("interest")
public class InterestAccount extends Account{

    @Override
    public String getType() {
        return "interest";
    }
}

package com.group3.exercise.bankapp.constants;

import java.util.Arrays;

public enum TransactionTypes {
    WITHDRAW("withdraw"),
    DEPOSIT("deposit");
    private final String value;

    private TransactionTypes(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static boolean isContaining(String value) {
        return Arrays.stream(TransactionTypes.values()).filter(s -> value.equals(s.value)).findAny().isPresent();
    }
}

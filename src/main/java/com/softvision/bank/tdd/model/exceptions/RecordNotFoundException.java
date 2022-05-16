package com.softvision.bank.tdd.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5545466885256769635L;

	public RecordNotFoundException() {
		super("No record exists for the given ID.");
	}

}

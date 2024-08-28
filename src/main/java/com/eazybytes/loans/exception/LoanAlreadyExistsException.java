package com.eazybytes.loans.exception;

public class LoanAlreadyExistsException extends RuntimeException{
    public LoanAlreadyExistsException(String str) {
        super(str);
    }
}

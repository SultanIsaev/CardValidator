package com.service;

import com.model.CreditCard;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Validator {
    public boolean validate(@WebParam(name = "Credit-Card") CreditCard creditCard);
}

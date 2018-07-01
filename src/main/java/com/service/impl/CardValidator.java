package com.service.impl;

import com.model.CreditCard;
import com.service.Validator;

import javax.jws.WebService;

@WebService(endpointInterface = "com.service.Validator",
            portName = "CreditCardValidator",
            serviceName = "CardValidatorService")
public class CardValidator implements Validator {

    public boolean validate(CreditCard creditCard){
        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length()-1);

        if(Integer.parseInt(lastDigit.toString()) % 2 == 0){
            return true;
        }else {
            return false;
        }
    }
}
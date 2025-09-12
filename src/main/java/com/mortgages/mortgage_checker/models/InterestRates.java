package com.mortgages.mortgage_checker.models;

import java.sql.Timestamp;
import com.mortgages.mortgage_checker.models.InterestRate;
import java.util.List;
import java.util.ArrayList;

public class InterestRates {
    private List<InterestRate> rates;

    public InterestRates(){
        rates = new ArrayList<InterestRate>();
    }

    public List<InterestRate> getRates(){
        return rates;
    }
}

package com.mortgages.mortgage_checker.models;

import java.sql.Timestamp;
import com.mortgages.mortgage_checker.models.InterestRate;
import java.util.List;
import java.util.ArrayList;

public class InterestRates {
    private List<InterestRate> rates;

    public InterestRates(){
        rates = new ArrayList<InterestRate>();

        rates.add( new InterestRate(12,3, new Timestamp(System.currentTimeMillis())));
        rates.add( new InterestRate(24,3.2, new Timestamp(System.currentTimeMillis())));
        rates.add( new InterestRate(60,3.3, new Timestamp(System.currentTimeMillis())));
        rates.add( new InterestRate(120,3.4, new Timestamp(System.currentTimeMillis())));
        rates.add( new InterestRate(360,3.5, new Timestamp(System.currentTimeMillis())));
    }

    public List<InterestRate> getRates(){
        return rates;
    }
}

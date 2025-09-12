package com.mortgages.mortgage_checker.models;

import java.util.List;
import java.util.ArrayList;

public class InterestRates {
    private List<InterestRateRecord> rates;

    public InterestRates() {
        rates = new ArrayList<InterestRateRecord>();
    }

    public List<InterestRateRecord> getRates() {
        return rates;
    }

    public void setRates(List<InterestRateRecord> rates) {
        this.rates = rates;
    }
}

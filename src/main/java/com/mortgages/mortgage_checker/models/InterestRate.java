package com.mortgages.mortgage_checker.models;

import java.sql.Timestamp;

public class InterestRate {
    private Integer maturityPeriod;
    private double interestRate;
    private Timestamp lastUpdate;

    public InterestRate(){}
    public InterestRate(Integer maturityPeriod, double interestRate, Timestamp lastUpdate){
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdate = lastUpdate;
    }

    public Integer getMaturityPeriod(){return this.maturityPeriod;}
    public double getInterestRate(){return this.interestRate;}
    public Timestamp getLastUpdate(){return this.lastUpdate;}
}

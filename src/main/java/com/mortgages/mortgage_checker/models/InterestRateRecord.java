package com.mortgages.mortgage_checker.models;

import java.sql.Timestamp;

public class InterestRateRecord {
    private Integer maturityPeriod;
    private double interestRate;
    private Timestamp lastUpdate;

    public InterestRateRecord(){}
    public InterestRateRecord(Integer maturityPeriod, double interestRate, Timestamp lastUpdate){
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;
        this.lastUpdate = lastUpdate;
    }

    public Integer getMaturityPeriod(){return this.maturityPeriod;}
    public void setMaturityPeriod(Integer maturityPeriod){this.maturityPeriod=maturityPeriod;}
    
    public double getInterestRate(){return this.interestRate;}
    public void setInterestRate(double interestRate){this.interestRate = interestRate;}
    
    public Timestamp getLastUpdate(){return this.lastUpdate;}
    public void setLastUpdate(Timestamp timestamp){this.lastUpdate = timestamp;}
}

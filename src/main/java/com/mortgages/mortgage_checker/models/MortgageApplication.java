package com.mortgages.mortgage_checker.models;

public class MortgageApplication {
    private double income;
    private Integer maturityPeriod;
    private double loanValue;
    private double homeValue;

    public MortgageApplication(){}
    public double getIncome(){ return this.income;}
    public Integer getMaturityPeriod(){ return this.maturityPeriod;}
    public double getLoanValue(){ return this.loanValue;}
    public double getHomeValue(){ return this.homeValue;}
}
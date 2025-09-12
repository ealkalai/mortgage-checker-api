package com.mortgages.mortgage_checker.models;

public class MortgageApplication {
    private double income;
    private Integer maturityPeriod;
    private double loanValue;
    private double homeValue;

    public MortgageApplication(){}
    public MortgageApplication(double income, Integer maturityPeriod, double loanValue, double homeValue){
        this.income = income;
        this.maturityPeriod = maturityPeriod;
        this.loanValue = loanValue;
        this.homeValue = homeValue;
    }
    public double getIncome(){ return this.income;}
    public Integer getMaturityPeriod(){ return this.maturityPeriod;}
    public double getLoanValue(){ return this.loanValue;}
    public double getHomeValue(){ return this.homeValue;}
}
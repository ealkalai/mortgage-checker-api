package com.mortgages.mortgage_checker.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MortgageApplication {
    @NotNull
    @Positive(message="income must be positive number")
    private double income;

    @NotNull
    @Min(1)
    private Integer maturityPeriod;
    
    @NotNull
    @Min(1)
    private double loanValue;
    
    @NotNull
    @Min(1)
    private double homeValue;

    public MortgageApplication() {
    }

    public MortgageApplication(double income, Integer maturityPeriod, double loanValue, double homeValue) {
        this.income = income;
        this.maturityPeriod = maturityPeriod;
        this.loanValue = loanValue;
        this.homeValue = homeValue;
    }

    public double getIncome() {
        return this.income;
    }

    public void setIncome(double amount) {
        this.income = amount;
    }

    public Integer getMaturityPeriod() {
        return this.maturityPeriod;
    }

    public void setMaturityPeriod(int months) {
        this.maturityPeriod = months;
    }

    public double getLoanValue() {
        return this.loanValue;
    }

    public void setLoanValue(double amount) {
        this.loanValue = amount;
    }

    public double getHomeValue() {
        return this.homeValue;
    }

    public void setHomeValue(double amount) {
        this.homeValue = amount;
    }
}
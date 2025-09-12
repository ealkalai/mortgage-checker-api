package com.mortgages.mortgage_checker.models;

public class MortgageApplicationOutcome {
    private boolean isFeasible;
    private double amortization;

    private double loanValue;
    private double income;
    private double homeValue;
    private Integer maturityPeriod;
    private double interestRate;

    public MortgageApplicationOutcome() {
    }

    public MortgageApplicationOutcome(MortgageApplication application, InterestRateRecord mortgageRate) {
        this.loanValue = application.getLoanValue();
        this.income = application.getIncome();
        this.homeValue = application.getHomeValue();
        this.maturityPeriod = application.getMaturityPeriod();

        this.interestRate = mortgageRate.getInterestRate();

        this.isFeasible = checkFeasibility();
        if (this.isFeasible) {
            amortization = calculateMonthlyAmortization();
        }
    }

    public MortgageApplicationOutcome(double loanValue, double income, double homeValue, Integer maturityPeriod,
            double interestRate) {
        this.loanValue = loanValue;
        this.income = income;
        this.homeValue = homeValue;
        this.maturityPeriod = maturityPeriod;
        this.interestRate = interestRate;

        this.isFeasible = checkFeasibility();
        if (this.isFeasible) {
            amortization = calculateMonthlyAmortization();
        }
    }

    public boolean getIsFeasible() {
        return this.isFeasible;
    }

    public double getAmortization() {
        return this.amortization;
    }

    public void setAmortization(double amortization) {
        this.amortization = amortization;
    }

    private boolean checkFeasibility() {
        boolean result = true;
        if (loanValue > 4 * income)
            result = false;
        if (loanValue > homeValue)
            result = false;

        return result;
    }

    private double calculateMonthlyAmortization() {
        // Convert annual interest rate to monthly and percentage to decimal
        double monthlyRate = (interestRate / 100) / 12;
        int totalPayments = maturityPeriod;

        // Monthly amortization formula
        double monthlyPayment = (loanValue * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -totalPayments));

        monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        return monthlyPayment;
    }
}

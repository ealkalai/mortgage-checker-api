package com.mortgages.mortgage_checker.models;

import com.mortgages.mortgage_checker.models.InterestRate;
import com.mortgages.mortgage_checker.models.MortgageApplication;

public class MortgageApplicationOutcome {
    private MortgageApplication application;
    private InterestRate mortgageRate;
    private boolean isFeasible;
    private double amortization;

    public MortgageApplicationOutcome(){}
    public MortgageApplicationOutcome(MortgageApplication application, InterestRate mortgageRate){
        this.application = application;
        this.mortgageRate = mortgageRate;
        this.isFeasible = checkFeasibility();
        if (this.isFeasible){
            amortization = calculateMonthlyAmortization();
        }
    }
    
    public boolean isFeasible(){return this.isFeasible;}
    public double getAmortization(){return this.amortization;}
    
    private boolean checkFeasibility(){
        double loanValue = application.getLoanValue();
        double income = application.getIncome();
        double homeValue = application.getHomeValue();

        if( loanValue > 4 * income) return false;
        if( loanValue > homeValue ) return false;

        return true;
    }

    private double calculateMonthlyAmortization(){
        double loanValue = application.getLoanValue();
        int maturityPeriod = application.getMaturityPeriod();
        double interestRate = mortgageRate.getInterestRate();


        // Convert annual interest rate to monthly and percentage to decimal
        double monthlyRate = (interestRate / 100) / 12;
        int totalPayments = maturityPeriod * 12;
        
        // Monthly amortization formula
        double monthlyPayment = (loanValue * monthlyRate) /
                (1 - Math.pow(1 + monthlyRate, -totalPayments));

        return monthlyPayment;
    }
}

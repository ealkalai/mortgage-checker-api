package com.mortgages.mortgage_checker;

import org.springframework.web.bind.annotation.RestController;

import com.mortgages.mortgage_checker.models.InterestRateRecord;
import com.mortgages.mortgage_checker.models.InterestRates;
import com.mortgages.mortgage_checker.models.MortgageApplication;
import com.mortgages.mortgage_checker.models.MortgageApplicationOutcome;
import com.mortgages.mortgage_checker.models.exceptions.BadRequestException;
import com.mortgages.mortgage_checker.models.exceptions.MaturityNotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/")
public class MortgageCheckerController {

  private final InterestRatesLoaderService ratesInitializer;

  @Autowired
  public MortgageCheckerController(InterestRatesLoaderService ratesInitializer) {
    this.ratesInitializer = ratesInitializer;
  }

  @GetMapping("/interest-rates")
  public ResponseEntity<InterestRates> getAllInterestRates() {
    InterestRates rates = ratesInitializer.getRates();
    return new ResponseEntity<>(rates, HttpStatus.OK);
  }

  @PostMapping("/mortgage-check")
  public ResponseEntity<MortgageApplicationOutcome> postMortgageCheck(@Valid @RequestBody MortgageApplication application, BindingResult bindingResult) {
    if(bindingResult.hasFieldErrors()) 
      throw new BadRequestException("Bad request: input fields errors.");

    InterestRates rates = ratesInitializer.getRates();
    InterestRateRecord selectedRate = rates.getRates()
        .stream()
        .filter(p -> p.getMaturityPeriod().equals(application.getMaturityPeriod()))
        .findFirst()
        .orElse(null);
    if (selectedRate == null)
      throw new MaturityNotSupportedException("Select Maturity period from the interest rate list");

    MortgageApplicationOutcome outcome = new MortgageApplicationOutcome(application, selectedRate);
    return new ResponseEntity<>(outcome, HttpStatus.OK);
  }
}
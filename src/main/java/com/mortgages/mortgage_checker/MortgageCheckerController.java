package com.mortgages.mortgage_checker;

import org.springframework.web.bind.annotation.RestController;

import com.mortgages.mortgage_checker.models.InterestRate;
import com.mortgages.mortgage_checker.models.InterestRates;
import com.mortgages.mortgage_checker.models.MortgageApplication;
import com.mortgages.mortgage_checker.models.MortgageApplicationOutcome;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/")
public class MortgageCheckerController {

  public MortgageCheckerController() {
  }

  @GetMapping("/interest-rates")
  public ResponseEntity<InterestRates> GetAllInterestRates() {
    InterestRates rates = new InterestRates();
    return new ResponseEntity<>(rates, HttpStatus.OK);
  }
  
  @PostMapping("/mortgage-check")
  public ResponseEntity<MortgageApplicationOutcome> PostMortgageCheck(@RequestBody MortgageApplication application) {
    InterestRate selectedRate = (new InterestRates()).getRates().getFirst();
    
    MortgageApplicationOutcome outcome = new MortgageApplicationOutcome(application,selectedRate);
    return new ResponseEntity<>(outcome, HttpStatus.OK);
  }  
}
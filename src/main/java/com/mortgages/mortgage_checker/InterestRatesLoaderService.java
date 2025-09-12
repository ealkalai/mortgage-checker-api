package com.mortgages.mortgage_checker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortgages.mortgage_checker.models.InterestRates;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;


@Service
public class InterestRatesLoaderService {
    
    private InterestRates rates;
    private static final Logger logger = LoggerFactory.getLogger(InterestRatesLoaderService.class);

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File jsonFile = new ClassPathResource("rates-example.json").getFile();
            this.rates = objectMapper.readValue(jsonFile, InterestRates.class);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public InterestRates getRates(){
        return rates;
    }
}

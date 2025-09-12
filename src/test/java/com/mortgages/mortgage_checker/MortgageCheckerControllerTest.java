package com.mortgages.mortgage_checker;

import com.mortgages.mortgage_checker.models.MortgageApplication;
import com.mortgages.mortgage_checker.models.MortgageApplicationOutcome;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
class MortgageCheckerControllerTest {

	@Autowired
	private MockMvc mockMvc;

    @Test
	void getInterestRates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/interest-rates")
            .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	void shouldReturnFalseFeasibilityDueToLowIncome() throws Exception {
        double income = 70000.00;
        Integer maturityPeriod = 360;
        double loanValue = 5*income;
        double homeValue = loanValue;

		MortgageApplication application = new MortgageApplication(income, maturityPeriod, loanValue, homeValue);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mortgage-check")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(application)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isFeasible").value("false"));
	}
	
    @Test
	void shouldReturnFalseFeasibilityDueToLargerLoanOverHomeValue() throws Exception {
        double income = 70000.00;
        Integer maturityPeriod = 360;
        double homeValue = income;
        double loanValue = 2*homeValue;

		MortgageApplication application = new MortgageApplication(income, maturityPeriod, loanValue, homeValue);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mortgage-check")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(application)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isFeasible").value("false"));
	}
    
    @Test
	void shouldReturnBadRequestDueToWrongMaturityPeriod() throws Exception {
        double income = 70000.00;
        Integer maturityPeriod = 3600;
        double homeValue = income;
        double loanValue = 2*homeValue;

		MortgageApplication application = new MortgageApplication(income, maturityPeriod, loanValue, homeValue);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mortgage-check")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(application)))
            .andExpect(status().isBadRequest());
	}

    @Test
    void checkMonthlyPaymentResponse() throws Exception {
        double income = 100000.00;
        Integer maturityPeriod = 360;
        double homeValue = 300000;
        double loanValue = 300000;
        MortgageApplication application = new MortgageApplication(income, maturityPeriod, loanValue, homeValue);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/mortgage-check")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(application)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isFeasible").value("true"))
            .andExpect(jsonPath("$.amortization").value(1347.13));
    }
    
    @Test
    void checkMonthlyPaymentCalculations() {
        assertEquals(1347.13,(new MortgageApplicationOutcome(300000,100000,300000,360, 3.5)).getAmortization());
        assertEquals(2952.54,(new MortgageApplicationOutcome(300000,100000,300000,120, 3.4)).getAmortization());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


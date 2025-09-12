package com.mortgages.mortgage_checker;

import com.mortgages.mortgage_checker.models.MortgageApplication;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
	public void getInterestRates() throws Exception {
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
            .andExpect(jsonPath("$.feasible").value("false"));
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
            .andExpect(jsonPath("$.feasible").value("false"));
	}

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


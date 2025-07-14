package com.niharika.myproject.controller;


import com.niharika.myproject.record.RomanNumeralResponse;
import com.niharika.myproject.service.RomanNumeralService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RomanNumeralController.class)
public class RomanNumeralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RomanNumeralService service;

    private RomanNumeralResponse response;

    @Test
    public void romanNumeral() throws Exception {
        response = RomanNumeralResponse.builder().input(35).output("XXXV").build();
        when(service.convertToRomanNumeral(any())).thenReturn(response);
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/romannumeral?input=35"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.output").value("XXXV"));
    }

    @Test
    public void romanNumeral_largeInput_failure() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/romannumeral?input=4500"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void romanNumeral_lessThanZero_failure() throws Exception {
        mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/romannumeral?input=-356"))
                .andExpect(status().isBadRequest());
    }
}

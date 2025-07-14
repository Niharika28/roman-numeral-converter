package com.niharika.myproject.controller;

import com.niharika.myproject.enums.ErrorFactory;
import com.niharika.myproject.exception.WebApplicationException;
import com.niharika.myproject.record.RomanNumeralResponse;
import com.niharika.myproject.service.RomanNumeralService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RomanNumeralController {


    private final RomanNumeralService service;

    @GetMapping("/romannumeral")
    public RomanNumeralResponse romanNumeral(@RequestParam("input") Integer input) {
        log.info("Request received to convert number to numeral {}", input);
        if(input < 1 || input > 3999){
            log.info("Invalid input number. Number should be between 1 and 3999");
            throw new WebApplicationException(HttpStatus.BAD_REQUEST, ErrorFactory.INVALID_INPUT_NUMBER);
        }

        return service.convertToRomanNumeral(input);
    }
}

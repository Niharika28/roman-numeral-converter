package com.niharika.myproject.service;

import com.niharika.myproject.record.RomanNumeralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RomanNumeralServiceImpl implements RomanNumeralService {

    List<Integer> numbers = Arrays.asList(1000,900,500,400,100,90,50,40,10,9,5,4,1);

    List<String> romans = Arrays.asList("M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I");

    public RomanNumeralResponse convertToRomanNumeral(Integer input) {

        Integer initialInput = input;

        StringBuilder romanNumeral = new StringBuilder();

        for(int i=0;i< romans.size();i++){
            while(input - numbers.get(i) >= 0) {
                romanNumeral.append(romans.get(i));
                input -= numbers.get(i);
            }
        }
        log.info("For given Input Number:: {} Roman Numeral is ::",initialInput,romanNumeral);
        return RomanNumeralResponse.builder().input(initialInput).output(romanNumeral.toString()).build();
    }
}

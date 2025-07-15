package com.niharika.myproject.service;

import com.niharika.myproject.record.RomanNumeralResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * We have 7 distinct letters for representing roman numerals I,V,X,L,C,D,M
 * I can be placed before 5(V) and 10 (X) ex: IV = 5-1 =4, IX = 10-1 = 9
 * X can be placed before L and C ex: XL = 50-10=40, XC = 100-10=90
 * when a letter appears after a large letter, it is added ex: XII = 10 + 2= 12
 * when a letter appears before a large letter, it is subtracted ex: IX = 10-1=9
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RomanNumeralServiceImpl implements RomanNumeralService {
    // List of Integers corresponding to the roman numerals
    List<Integer> numbers = Arrays.asList(1000,900,500,400,100,90,50,40,10,9,5,4,1);

    // List of distinct possible Roman Numerals in descending order
    List<String> romans = Arrays.asList("M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I");

    public RomanNumeralResponse convertToRomanNumeral(Integer input) {

        Integer initialInput = input;

        StringBuilder romanNumeral = new StringBuilder();
        //iterating from romans list from 0 to 13
        for(int i=0;i< romans.size();i++){
            // if input - corresponding ith element is greater than zero
            while(input - numbers.get(i) >= 0) {
                // append it to string
                romanNumeral.append(romans.get(i));
                // subtract the number from the input
                input -= numbers.get(i);
            }
        }
        log.info("For given Input Number:: {} Roman Numeral is ::",initialInput,romanNumeral);
        return RomanNumeralResponse.builder().input(initialInput).output(romanNumeral.toString()).build();
    }
}

package com.niharika.myproject.service;


import com.niharika.myproject.record.RomanNumeralResponse;
import com.niharika.myproject.service.RomanNumeralService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class RomanNumeralServiceTest {

    private final RomanNumeralService service = new RomanNumeralServiceImpl();

    private RomanNumeralResponse response;

    @Test
    public void convertToRomanNumeral_success() throws Exception {
        response = RomanNumeralResponse.builder().input(35).output("XXXV").build();
        RomanNumeralResponse romanNumeralResponse = service.convertToRomanNumeral(35);
        assertEquals(romanNumeralResponse.output(), response.output());
    }
}

package com.niharika.myproject.exception;

import com.niharika.myproject.enums.ErrorFactory;
import com.niharika.myproject.enums.SysMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({WebApplicationException.class})
    protected ResponseEntity handleWebApplicationException(WebApplicationException exception){
        log.error("Web application Exception {}", exception.getMessage() );

        Map<String, List<SysMsg>> errors = new HashMap<>();
        List<SysMsg> response = new ArrayList<>();
        response.add(new SysMsg(ErrorFactory.INVALID_INPUT_NUMBER.getSeverity().name(), ErrorFactory.INVALID_INPUT_NUMBER.getDescription()));
        errors.put("errors",response);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errors,headers,exception.getHttpStatusCode());
    }
}

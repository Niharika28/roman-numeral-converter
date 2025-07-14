package com.niharika.myproject.exception;

import com.niharika.myproject.enums.ErrorFactory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WebApplicationException extends RuntimeException {

    private final HttpStatus httpStatusCode;
    private final ErrorFactory errorFactory;

    public WebApplicationException(@NotNull HttpStatus httpStatusCode, ErrorFactory errorFactory) {
        super(errorFactory.getDescription());
        this.httpStatusCode = httpStatusCode;
        this.errorFactory = errorFactory;
    }
}

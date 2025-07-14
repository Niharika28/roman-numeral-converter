package com.niharika.myproject.enums;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorFactory {

    //404 Errors
    INVALID_INPUT_NUMBER(4041, "Number must be between 1 and 3999", EnumErrorSeverities.WARNING);

    @NotNull
    private final Integer code;

    @NotNull
    private final String description;

    @NotNull
    private final EnumErrorSeverities severity;
}

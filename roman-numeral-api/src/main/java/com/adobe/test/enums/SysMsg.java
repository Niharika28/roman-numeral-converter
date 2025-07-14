package com.niharika.myproject.enums;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.ALWAYS)
@AllArgsConstructor
@Getter
@Setter
public class SysMsg {
    String code;
    String title;
}

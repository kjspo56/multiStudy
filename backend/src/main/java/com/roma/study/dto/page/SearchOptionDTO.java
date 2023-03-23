package com.roma.study.dto.page;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchOptionDTO {
    @NotNull(message = "{searchOption.key.notNull}")
    private String key;
    @NotNull(message = "{search.option.value.required}")
    private Object value;
    /**
     * : == equals (default)
     * < == less than
     * <= greater than
     * c == contains
     * s == startWith
     * e == endWith
     */
    private String operation = ":";
}

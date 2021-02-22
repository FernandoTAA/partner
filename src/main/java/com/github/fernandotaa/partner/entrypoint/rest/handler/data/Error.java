package com.github.fernandotaa.partner.entrypoint.rest.handler.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Error representation.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class Error {
    private String scope;
    private String field;
    private String value;
    private String message;
}

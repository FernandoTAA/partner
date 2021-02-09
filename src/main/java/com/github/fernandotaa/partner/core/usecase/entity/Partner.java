package com.github.fernandotaa.partner.core.usecase.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * Representation of a unique Partner in requests.
 */
@Builder
public class Partner {
    private String tradingName;
    private String ownerName;
    private String document;
}

package com.github.fernandotaa.partner.core.usecase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representation of a unique Partner in requests.
 */
@Getter
@AllArgsConstructor
public class Partner {
    private String tradingName;
    private String ownerName;
    private String document;
}

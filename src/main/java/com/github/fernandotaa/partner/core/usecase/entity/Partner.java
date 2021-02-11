package com.github.fernandotaa.partner.core.usecase.entity;

import lombok.Getter;

/**
 * Representation of a unique Partner in requests.
 */
@Getter
public class Partner extends PartnerBase {
    private String id;

    public Partner(String tradingName, String ownerName, String document, String id) {
        super(tradingName, ownerName, document);
        this.id = id;
    }
}

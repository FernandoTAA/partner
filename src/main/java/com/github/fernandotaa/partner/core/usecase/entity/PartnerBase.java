package com.github.fernandotaa.partner.core.usecase.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PartnerBase {
    private String tradingName;
    private String ownerName;
    private String document;
}

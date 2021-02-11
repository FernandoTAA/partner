package com.github.fernandotaa.partner.entrypoint.rest.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PartnerResponse {
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;

    public static PartnerResponse from(Partner partner) {
        return new PartnerResponse(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument()
        );
    }
}

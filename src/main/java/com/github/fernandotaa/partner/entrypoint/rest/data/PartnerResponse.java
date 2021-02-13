package com.github.fernandotaa.partner.entrypoint.rest.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PartnerResponse {
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    private GeoJsonMultiPolygon coverageArea;
    private GeoJsonPoint address;


    public static PartnerResponse from(Partner partner) {
        return new PartnerResponse(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                partner.getCoverageArea(),
                partner.getAddress()
        );
    }
}

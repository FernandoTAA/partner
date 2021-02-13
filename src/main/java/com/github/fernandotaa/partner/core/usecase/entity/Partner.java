package com.github.fernandotaa.partner.core.usecase.entity;

import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Representation of a unique Partner in requests.
 */
@Getter
@NoArgsConstructor
public class Partner extends PartnerBase {
    private String id;

    public Partner(
            String id,
            String tradingName,
            String ownerName,
            String document,
            GeoJsonMultiPolygon coverageArea,
            GeoJsonPoint address
    ) {
        super(tradingName, ownerName, document, coverageArea, address);
        this.id = id;
    }
}

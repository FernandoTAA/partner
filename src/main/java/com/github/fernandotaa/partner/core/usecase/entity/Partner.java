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

    /**
     * Construction with all fields.
     *
     * @param id - Identification
     * @param tradingName - Trading Name
     * @param ownerName - Owner Name
     * @param document - Document
     * @param coverageArea - Coverage Area
     * @param address - Address
     */
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

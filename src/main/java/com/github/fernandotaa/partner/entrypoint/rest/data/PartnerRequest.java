package com.github.fernandotaa.partner.entrypoint.rest.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Representation of a unique Partner in requests.
 */
@Getter
@Setter
@ToString
public class PartnerRequest {
    @NotBlank
    private String tradingName;
    @NotBlank
    private String ownerName;
    @NotBlank
    private String document;
    @NotNull
    private GeoJsonMultiPolygon coverageArea;
    @NotNull
    private GeoJsonPoint address;

    /**
     * Convert {@link PartnerRequest} to {@link Partner}.
     *
     * @return - {@link Partner}
     */
    public PartnerBase toEntity() {
        return new PartnerBase(tradingName, ownerName, document, coverageArea, address);
    }
}

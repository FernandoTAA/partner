package com.github.fernandotaa.partner.gateway.repository.mongodb.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of Partner in MongoDB.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document("partner")
public class PartnerMongoDB {
    @Id
    private String id;
    private String tradingName;
    private String ownerName;
    private String document;
    @GeoSpatialIndexed
    private GeoJsonMultiPolygon coverageArea;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint address;

    public static PartnerMongoDB from(PartnerBase partner) {
        return new PartnerMongoDB(
                null,
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                null,
                null
        );
    }

    public static Partner toEntity(PartnerMongoDB partner) {
        var geoJsonMultiPolygonAdapter = new GeoJsonMultiPolygonAdapter(partner.coverageArea);
        var geoJsonPointAdapter = new GeoJsonPointAdapter(partner.address);
        return new Partner(
                partner.getId(),
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                geoJsonMultiPolygonAdapter.adapt(),
                geoJsonPointAdapter.adapt()
        );
    }
}

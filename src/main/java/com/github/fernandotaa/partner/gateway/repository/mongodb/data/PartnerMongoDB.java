package com.github.fernandotaa.partner.gateway.repository.mongodb.data;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonMongoDBAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointMongoDBAdapter;
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
    private GeoJsonMultiPolygon coverageArea;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint address;

    /**
     * Factory to create {@link PartnerMongoDB} from {@link PartnerBase}.
     *
     * @param partner - {@link PartnerBase}
     * @return - {@link PartnerMongoDB}
     */
    public static PartnerMongoDB from(PartnerBase partner) {
        var geoJsonMultiPolygonMongoDBAdapter = new GeoJsonMultiPolygonMongoDBAdapter(partner.getCoverageArea());
        var geoJsonPointMongoDBAdapter = new GeoJsonPointMongoDBAdapter(partner.getAddress());
        return new PartnerMongoDB(
                null,
                partner.getTradingName(),
                partner.getOwnerName(),
                partner.getDocument(),
                geoJsonMultiPolygonMongoDBAdapter.adapt(),
                geoJsonPointMongoDBAdapter.adapt()
        );
    }

    /**
     * Convert {@link Partner} to {@link PartnerMongoDB}.
     *
     * @param partner - {@link PartnerMongoDB}
     * @return - {@link Partner}
     */
    public static Partner toEntity(PartnerMongoDB partner) {
        var geoJsonMultiPolygonAdapter = new GeoJsonMultiPolygonAdapter(partner.getCoverageArea());
        var geoJsonPointAdapter = new GeoJsonPointAdapter(partner.getAddress());
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

package com.github.fernandotaa.partner.gateway.repository.mongodb.adapter;

import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AllArgsConstructor;

import java.util.Objects;

/**
 * Adapter to convert {@link GeoJsonPoint} to {@link com.github.fernandotaa.partner.library.geojson.GeoJsonPoint}
 */
@AllArgsConstructor
public class GeoJsonPointMongoDBAdapter {
    private final GeoJsonPoint point;

    public org.springframework.data.mongodb.core.geo.GeoJsonPoint adapt() {
        if (Objects.isNull(point)) {
            return null;
        }
        return new org.springframework.data.mongodb.core.geo.GeoJsonPoint(point.getCoordinates().get(0), point.getCoordinates().get(1));
    }
}

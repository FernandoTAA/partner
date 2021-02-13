package com.github.fernandotaa.partner.gateway.repository.mongodb.adapter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Objects;

/**
 * Adapter to convert {@link org.springframework.data.mongodb.core.geo.GeoJsonPoint} to {@link com.github.fernandotaa.partner.library.geojson.GeoJsonPoint}
 */
@AllArgsConstructor
public class GeoJsonPointAdapter {
    private final GeoJsonPoint point;

    public com.github.fernandotaa.partner.library.geojson.GeoJsonPoint adapt() {
        if (Objects.isNull(point)) {
            return null;
        }
        return new com.github.fernandotaa.partner.library.geojson.GeoJsonPoint(point.getX(), point.getY());
    }
}

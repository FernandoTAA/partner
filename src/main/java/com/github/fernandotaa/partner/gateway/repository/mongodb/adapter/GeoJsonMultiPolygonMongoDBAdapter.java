package com.github.fernandotaa.partner.gateway.repository.mongodb.adapter;

import lombok.AllArgsConstructor;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Adapter to convert {@link GeoJsonMultiPolygon} to {@link com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon}
 */
@AllArgsConstructor
public class GeoJsonMultiPolygonMongoDBAdapter {
    private final GeoJsonMultiPolygon multiPolygon;

    public org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon adapt() {
        if (Objects.isNull(multiPolygon)) {
            return null;
        }
        List<GeoJsonPolygon> coordinates;
        return new org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon(coordinates);
    }
}

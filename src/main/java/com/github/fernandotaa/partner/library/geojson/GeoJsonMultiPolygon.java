package com.github.fernandotaa.partner.library.geojson;

import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Representation of MultiPolygon from {@link GeoJson}.
 * For more information: https://en.wikipedia.org/wiki/GeoJSON
 */
@Getter
@ToString
public class GeoJsonMultiPolygon extends GeoJson {
    private List<List<List<List<Double>>>> coordinates;

    public GeoJsonMultiPolygon() {
        super("MultiPolygon");
    }

    public GeoJsonMultiPolygon(List<List<List<List<Double>>>> coordinates) {
        this();
        this.coordinates = coordinates;
    }
}

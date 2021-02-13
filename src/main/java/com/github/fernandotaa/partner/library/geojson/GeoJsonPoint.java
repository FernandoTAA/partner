package com.github.fernandotaa.partner.library.geojson;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Representation of Point from {@link GeoJson}.
 * For more information: https://en.wikipedia.org/wiki/GeoJSON
 */
@Getter
@ToString
@EqualsAndHashCode
public class GeoJsonPoint extends GeoJson {
    private List<Double> coordinates;

    public GeoJsonPoint() {
        super("Point");
    }

    public GeoJsonPoint(Double longitude, Double latitude) {
        this();
        this.coordinates = List.of(longitude, latitude);
    }
}

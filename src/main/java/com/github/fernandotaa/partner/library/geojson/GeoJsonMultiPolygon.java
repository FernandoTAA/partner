package com.github.fernandotaa.partner.library.geojson;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Representation of MultiPolygon from {@link GeoJson}.
 * For more information: https://en.wikipedia.org/wiki/GeoJSON
 */
@Getter
@ToString
@EqualsAndHashCode
public class GeoJsonMultiPolygon extends GeoJson {
    private List<List<List<List<Double>>>> coordinates;

    /**
     * Default constructor specifying type as "MultiPolygon".
     */
    public GeoJsonMultiPolygon() {
        super("MultiPolygon");
    }

    /**
     * Construct specifying MultiPolygon coordinates.
     *
     * @param coordinates - MultiPolygon coordinates
     */
    public GeoJsonMultiPolygon(List<List<List<List<Double>>>> coordinates) {
        this();
        this.coordinates = coordinates;
    }
}

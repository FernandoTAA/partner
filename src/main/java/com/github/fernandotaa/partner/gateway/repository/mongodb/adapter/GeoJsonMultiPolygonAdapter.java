package com.github.fernandotaa.partner.gateway.repository.mongodb.adapter;

import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Adapter to convert {@link org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon} to {@link com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon}
 */
@AllArgsConstructor
public class GeoJsonMultiPolygonAdapter {
    private final GeoJsonMultiPolygon multiPolygon;

    /**
     * Adapt object from {@link GeoJsonMultiPolygon} to {@link com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon}.
     *
     * @return - {@link com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon}
     */
    public com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon adapt() {
        if (Objects.isNull(multiPolygon)) {
            return null;
        }
        ArrayList<List<List<List<Double>>>> coordinates = extractCoordinates(multiPolygon);
        return new com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon(coordinates);
    }

    private ArrayList<List<List<List<Double>>>> extractCoordinates(GeoJsonMultiPolygon multiPolygon) {
        var coordinates = new ArrayList<List<List<List<Double>>>>();
        for (GeoJsonPolygon polygon : multiPolygon.getCoordinates()) {
            var polygonList = new ArrayList<List<List<Double>>>();
            coordinates.add(polygonList);
            for (GeoJsonLineString lineString : polygon.getCoordinates()) {
                var lineStringList = new ArrayList<List<Double>>();
                polygonList.add(lineStringList);
                for (Point point : lineString.getCoordinates()) {
                    var pointList = List.of(point.getX(), point.getY());
                    lineStringList.add(pointList);
                }
            }
        }
        return coordinates;
    }
}

package com.github.fernandotaa.partner.gateway.repository.mongodb.adapter;

import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import lombok.AllArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        var coordinates = new ArrayList<GeoJsonPolygon>();
        for (List<List<List<Double>>> coordinate : multiPolygon.getCoordinates()) {
            var firstListOfPoints = exatractPoints(coordinate.get(0));
            var polygon = new GeoJsonPolygon(firstListOfPoints);
            coordinates.add(polygon);
        }
        return new org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon(coordinates);
    }

    private List<Point> exatractPoints(List<List<Double>> points) {
        return points.stream()
                .map(list -> new Point(list.get(0), list.get(1)))
                .collect(Collectors.toList());
    }
}

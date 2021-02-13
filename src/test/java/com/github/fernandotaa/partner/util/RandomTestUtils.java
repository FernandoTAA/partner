package com.github.fernandotaa.partner.util;

import br.com.six2six.fixturefactory.function.impl.CnpjFunction;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Utility with static methods for random actions.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final public class RandomTestUtils {

    /**
     * Generate a random integer obeying min and max rule.
     *
     * @param min - Minimum value to return
     * @param max - Maximum value to return
     * @return - Random integer value
     */
    public static Integer integer(Integer min, Integer max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Generate a random double precision obeying min and max rule.
     *
     * @param min - Minimum value to return
     * @param max - Maximum value to return
     * @return - Random double precision value
     */
    public static Double doublePrecision(Double min, Double max) {
        return Math.random() * (max - min + 1) + min;
    }

    /**
     * Generate a random document with pattern "{number}/{number}"
     * Example: 02.453.716/000170
     *
     * @return
     */
    public static String document() {
        return new CnpjFunction(true).generateValue();
    }

    /**
     * Generate a random {@link UUID} and return a {@link String}.
     * Example: 1ae88ef5-f1f9-4797-b196-98ec141e24f1
     *
     * @return - {@link UUID} in {@link String}
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generate a {@link GeoJsonPoint} with random coordinates.
     *
     * @return - {@link GeoJsonPoint}
     */
    public static GeoJsonPoint point() {
        return new GeoJsonPoint(doublePrecision(180D, -180D), doublePrecision(90D, -90D));
    }

    /**
     * Generate a {@link GeoJsonMultiPolygon} with random coordinates.
     *
     * @return - {@link GeoJsonMultiPolygon}
     */
    public static GeoJsonMultiPolygon multiPolygon() {
        var coordinates = new ArrayList<List<List<List<Double>>>>();
        for (int i = 0; i < integer(1, 5); i++) {
            var polygon = new ArrayList<List<List<Double>>>();
            coordinates.add(polygon);
            var lineString = new ArrayList<List<Double>>();
            polygon.add(lineString);
            for (int k = 0; k < integer(4, 20); k++) {
                var point = List.of(doublePrecision(180D, -180D), doublePrecision(90D, -90D));
                lineString.add(point);
            }
        }
        return new GeoJsonMultiPolygon(coordinates);
    }

    /**
     * Generate a {@link GeoJsonPoint} with random coordinates.
     *
     * @return - {@link GeoJsonPoint}
     */
    public static org.springframework.data.mongodb.core.geo.GeoJsonPoint pointMongo() {
        return new org.springframework.data.mongodb.core.geo.GeoJsonPoint(doublePrecision(180D, -180D), doublePrecision(90D, -90D));
    }

    /**
     * Generate a {@link org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon} with random coordinates.
     *
     * @return - {@link org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon}
     */
    public static org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon multiPolygonMongo() {
        var polygons = new ArrayList<GeoJsonPolygon>();
        for (int i = 0; i < integer(1, 5); i++) {
            var geoJsonPolygon = new GeoJsonPolygon(generateListOfPoints());
            polygons.add(geoJsonPolygon);
        }
        return new org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon(polygons);
    }

    private static List<Point> generateListOfPoints() {
        var lineString = new ArrayList<Point>();
        for (int j = 0; j < integer(4, 20); j++) {
            var point = new Point(doublePrecision(180D, -180D), doublePrecision(90D, -90D));
            lineString.add(point);
        }
        return lineString;
    }
}

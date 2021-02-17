package com.github.fernandotaa.partner.test.gateway.repository.mongodb.adapter;


import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonMongoDBAdapter;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Test cases GeoJsonPointAdapter")
public class GeoJsonMultiPolygonMongoDBAdapterTest {
    @Test
    @DisplayName("Success test case")
    void success() {
        var multiPolygon = RandomTestUtils.multiPolygon();
        var adapter = new GeoJsonMultiPolygonMongoDBAdapter(multiPolygon);
        var converted = adapter.adapt();

        assertAll(
                () -> assertThat(converted).isNotNull()
                        .extracting(GeoJsonMultiPolygon::getCoordinates).isNotNull().asList().isNotNull()
                        .hasSize(multiPolygon.getCoordinates().size()).element(0).isNotNull()
                        .extracting(polygon -> ((GeoJsonPolygon) polygon).getCoordinates()).isNotNull().asList().isNotNull().element(0).isNotNull()
                        .extracting(lineString -> ((GeoJsonLineString) lineString).getCoordinates()).isNotNull().asList().isNotNull().element(0).isNotNull()
                        .extracting(point -> ((Point) point).getX()).isNotNull().isEqualTo(multiPolygon.getCoordinates().get(0).get(0).get(0).get(0)),
                () -> assertThat(converted).isNotNull()
                        .extracting(GeoJsonMultiPolygon::getCoordinates).isNotNull().asList().isNotNull().element(0).isNotNull()
                        .extracting(polygon -> ((GeoJsonPolygon) polygon).getCoordinates()).isNotNull().asList().isNotNull().element(0).isNotNull()
                        .extracting(lineString -> ((GeoJsonLineString) lineString).getCoordinates()).isNotNull().asList().isNotNull().element(0).isNotNull()
                        .extracting(point -> ((Point) point).getY()).isNotNull().isEqualTo(multiPolygon.getCoordinates().get(0).get(0).get(0).get(1))
        );
    }

    @Test
    @DisplayName("Success test case with null as GeoJsonMultiPolygon")
    void null_success() {
        var adapter = new GeoJsonMultiPolygonAdapter(null);
        var converted = adapter.adapt();
        assertThat(converted).isNull();
    }
}

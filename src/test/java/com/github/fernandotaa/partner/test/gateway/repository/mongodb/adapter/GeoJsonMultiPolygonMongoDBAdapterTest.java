package com.github.fernandotaa.partner.test.gateway.repository.mongodb.adapter;


import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Test cases GeoJsonPointAdapter")
public class GeoJsonMultiPolygonMongoDBAdapterTest {
    @Test
    @DisplayName("Success test case")
    void success() {
        var multiPolygon = RandomTestUtils.multiPolygonMongo();
        var adapter = new GeoJsonMultiPolygonAdapter(multiPolygon);
        var converted = adapter.adapt();
        assertAll(
                () -> assertThat(converted).isNotNull()
                        .extracting(GeoJsonMultiPolygon::getCoordinates).isNotNull().asList().hasSize(multiPolygon.getCoordinates().size())
                        .element(0).isNotNull().asList().hasSize(multiPolygon.getCoordinates().get(0).getCoordinates().size())
                        .element(0).isNotNull().asList().hasSize(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().size()),
                () -> assertThat(converted).isNotNull()
                        .extracting(GeoJsonMultiPolygon::getCoordinates).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(0).isNotNull().isEqualTo(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0).getX()),
                () -> assertThat(converted).isNotNull()
                        .extracting(GeoJsonMultiPolygon::getCoordinates).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(0).isNotNull().asList()
                        .element(1).isNotNull().isEqualTo(multiPolygon.getCoordinates().get(0).getCoordinates().get(0).getCoordinates().get(0).getY())
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

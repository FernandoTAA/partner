package com.github.fernandotaa.partner.test.gateway.repository.mongodb.adapter;

import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointAdapter;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Test cases GeoJsonPointAdapter")
public class GeoJsonPointAdapterTest {
    @Test
    @DisplayName("Success test case")
    void success() {
        var point = RandomTestUtils.pointMongo();
        var adapter = new GeoJsonPointAdapter(point);
        var converted = adapter.adapt();
        assertAll(
                () -> assertThat(converted).isNotNull().extracting(GeoJsonPoint::getCoordinates).isNotNull().asList().hasSize(2).element(0).isNotNull().isEqualTo(point.getX()),
                () -> assertThat(converted).isNotNull().extracting(GeoJsonPoint::getCoordinates).isNotNull().asList().hasSize(2).element(1).isNotNull().isEqualTo(point.getY())
        );
    }

    @Test
    @DisplayName("Success test case with null as Point")
    void null_success() {
        var adapter = new GeoJsonPointAdapter(null);
        var converted = adapter.adapt();
        assertThat(converted).isNull();
    }
}

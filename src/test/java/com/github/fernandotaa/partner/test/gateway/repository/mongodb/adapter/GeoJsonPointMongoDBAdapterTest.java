package com.github.fernandotaa.partner.test.gateway.repository.mongodb.adapter;

import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointMongoDBAdapter;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Test cases GeoJsonPointAdapter")
public class GeoJsonPointMongoDBAdapterTest {
    @Test
    @DisplayName("Success test case")
    void success() {
        var point = RandomTestUtils.point();
        var adapter = new GeoJsonPointMongoDBAdapter(point);
        var converted = adapter.adapt();
        assertAll(
                () -> assertThat(converted).isNotNull().extracting(GeoJsonPoint::getCoordinates).isNotNull().asList().hasSize(2).element(0).isNotNull().isEqualTo(point.getCoordinates().get(0)),
                () -> assertThat(converted).isNotNull().extracting(GeoJsonPoint::getCoordinates).isNotNull().asList().hasSize(2).element(1).isNotNull().isEqualTo(point.getCoordinates().get(1))
        );
    }

    @Test
    @DisplayName("Success test case with null as Point")
    void null_success() {
        var adapter = new GeoJsonPointMongoDBAdapter(null);
        var converted = adapter.adapt();
        assertThat(converted).isNull();
    }
}

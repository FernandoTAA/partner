package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.library.geojson.GeoJsonMultiPolygon;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import com.github.javafaker.Faker;

import java.util.List;
import java.util.Optional;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomTestUtils.integer;

/**
 * Static method to use in {@link MainFixture} for {@link GeoJsonPoint} and {@link GeoJsonMultiPolygon} to use in Partner Tests;
 */
public class GeoJsonFixture {
    static void loadInCoverageAreaGeoJsonPoint() {
        Fixture.of(GeoJsonPoint.class)
                .addTemplate("in_coverage_area", new Rule() {{
                    add("coordinates", function(() -> generateGeoJsonPointAddressInCoverageArea().getCoordinates()));
                }});
    }

    static void loadOutOfCoverageAreaGeoJsonPoint() {
        Fixture.of(GeoJsonPoint.class)
                .addTemplate("out_of_coverage_area", new Rule() {{
                    add("coordinates", function(() -> generateGeoJsonPointAddressOutOfCoverageArea().getCoordinates()));
                }});
    }

    private static GeoJsonPoint generateGeoJsonPointAddressOutOfCoverageArea() {
        var longitude = -43.297337 + 2;
        var latitude = -23.013538 + 2;
        return new GeoJsonPoint(longitude, latitude);
    }

    protected static GeoJsonPoint generateGeoJsonPointAddressInCoverageArea() {
        var longitude = -43.297337 + (RandomTestUtils.doublePrecision(-20D, 20D) * 0.000001);
        var latitude = -23.013538 + (RandomTestUtils.doublePrecision(-20D, 20D) * 0.000001);
        return new GeoJsonPoint(longitude, latitude);
    }

    protected static GeoJsonMultiPolygon generateGeoJsonMultiPolygonCoverageArea() {
        List<List<List<List<Double>>>> coordinates = List.of(
                List.of(
                        List.of(
                                List.of(-43.36556, -22.99669),
                                List.of(-43.36539, -23.01928),
                                List.of(-43.26583, -23.01802),
                                List.of(-43.25724, -23.00649),
                                List.of(-43.23355, -23.00127),
                                List.of(-43.2381, -22.99716),
                                List.of(-43.23866, -22.99649),
                                List.of(-43.24063, -22.99756),
                                List.of(-43.24634, -22.99736),
                                List.of(-43.24677, -22.99606),
                                List.of(-43.24067, -22.99381),
                                List.of(-43.24886, -22.99121),
                                List.of(-43.25617, -22.99456),
                                List.of(-43.25625, -22.99203),
                                List.of(-43.25346, -22.99065),
                                List.of(-43.29599, -22.98283),
                                List.of(-43.3262, -22.96481),
                                List.of(-43.33427, -22.96402),
                                List.of(-43.33616, -22.96829),
                                List.of(-43.342, -22.98157),
                                List.of(-43.34817, -22.97967),
                                List.of(-43.35142, -22.98062),
                                List.of(-43.3573, -22.98084),
                                List.of(-43.36522, -22.98032),
                                List.of(-43.36696, -22.98422),
                                List.of(-43.36717, -22.98855),
                                List.of(-43.36636, -22.99351),
                                List.of(-43.36556, -22.99669)
                        )
                )
        );
        return new GeoJsonMultiPolygon(coordinates);
    }
}

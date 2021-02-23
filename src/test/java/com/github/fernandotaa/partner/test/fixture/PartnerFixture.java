package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import com.github.javafaker.Faker;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;

/**
 * Static method to use in {@link MainFixture} for {@link PartnerFixture} to use in Partner Tests;
 */
public class PartnerFixture {
    static void loadValidPartner() {
        Fixture.of(Partner.class)
                .addTemplate("valid", new Rule() {{
                    add("id", function(RandomTestUtils::uuid));
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                    add("address", function(RandomTestUtils::point));
                }});
    }

    static void loadValidPartnerBase() {
        Fixture.of(PartnerBase.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                    add("address", function(RandomTestUtils::point));
                }});
    }

    static void loadInCoverageAreaPartnerBase() {
        Fixture.of(PartnerBase.class)
                .addTemplate("in_coverage_area", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("coverageArea", function(GeoJsonFixture::generateGeoJsonMultiPolygonCoverageArea));
                    add("address", function(GeoJsonFixture::generateGeoJsonPointAddressInCoverageArea));
                }});
    }
}

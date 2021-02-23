package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerRequest;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import com.github.javafaker.Faker;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;

/**
 * Static method to use in {@link MainFixture} for {@link PartnerRequest} to use in Partner Tests;
 */
public class PartnerRequestFixture {
    static void loadValidPartnerRequest() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestTradingNameNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_ownerName_null", new Rule() {{
                    add("tradingName", null);
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestTradingNameEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_ownerName_empty", new Rule() {{
                    add("tradingName", "");
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestOwnerNameNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_tradingName_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", null);
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestOwnerNameEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_tradingName_empty", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", "");
                    add("document", function(RandomTestUtils::document));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestDocumentNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_document_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", null);
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestDocumentEmpty() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_document_empty", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", "");
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestAddressNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_address_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(Faker.instance().name()::name));
                    add("address", null);
                    add("coverageArea", function(RandomTestUtils::multiPolygon));
                }});
    }

    static void loadInvalidPartnerRequestCoverageAreaNull() {
        Fixture.of(PartnerRequest.class)
                .addTemplate("invalid_coverageArea_null", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(Faker.instance().name()::name));
                    add("address", function(RandomTestUtils::point));
                    add("coverageArea", null);
                }});
    }
}

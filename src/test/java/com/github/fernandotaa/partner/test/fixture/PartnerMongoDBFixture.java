package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import com.github.javafaker.Faker;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;

/**
 * Static method to use in {@link MainFixture}  for {@link PartnerMongoDB} to use in Partner Tests;
 */
public class PartnerMongoDBFixture {
    static void loadValidPartner() {
        Fixture.of(PartnerMongoDB.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("coverageArea", function(RandomTestUtils::multiPolygonMongo));
                    add("address", function(RandomTestUtils::pointMongo));
                }});
    }

    static void loadSavedPartner() {
        Fixture.of(PartnerMongoDB.class)
                .addTemplate("saved", new Rule() {{
                    add("id", function(RandomTestUtils::uuid));
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomTestUtils::document));
                    add("coverageArea", function(RandomTestUtils::multiPolygonMongo));
                    add("address", function(RandomTestUtils::pointMongo));
                }});
    }
}

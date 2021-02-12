package com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomUtils;
import com.github.javafaker.Faker;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;

/**
 * Fixture template loader for {@link PartnerMongoDB} to use in Partner Tests;
 */
public class PartnerMongoDBFixture implements TemplateLoader {
    @Override
    public void load() {
        loadValidPartner();
    }

    private void loadValidPartner() {
        Fixture.of(PartnerMongoDB.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomUtils::document));
                }});
    }
}

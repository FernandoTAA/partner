package com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.util.RandomUtils;
import com.github.javafaker.Faker;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomUtils.integer;

public class PartnerFixture implements TemplateLoader {
    @Override
    public void load() {
        loadValidPartner();
        loadValidPartnerBatchRequest();
    }

    private void loadValidPartner() {
        Fixture.of(Partner.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomUtils::document));
                }});
    }

    private void loadValidPartnerBatchRequest() {
        Fixture.of(PartnerSaverInputValues.class)
                .addTemplate("valid", new Rule() {{
                    add("partners", Fixture.from(Partner.class).gimme(integer(1, 20), "valid"));
                }});
    }
}

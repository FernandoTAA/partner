package com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdOutputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.util.RandomUtils;
import com.github.javafaker.Faker;

import java.util.Optional;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomUtils.integer;

/**
 * Fixture template loader for {@link PartnerFixture} to use in Partner Tests;
 */
public class PartnerFixture implements TemplateLoader {
    @Override
    public void load() {
        loadValidPartnerBase();
        loadValidPartner();
        loadValidPartnerSaverInputValues();
        loadValidPartnerGetterByIdOutputValues();
    }

    private void loadValidPartnerBase() {
        Fixture.of(PartnerBase.class)
                .addTemplate("valid", new Rule() {{
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomUtils::document));
                }});
    }

    private void loadValidPartner() {
        Fixture.of(Partner.class)
                .addTemplate("valid", new Rule() {{
                    add("id", function(RandomUtils::uuid));
                    add("tradingName", function(Faker.instance().company()::name));
                    add("ownerName", function(Faker.instance().name()::name));
                    add("document", function(RandomUtils::document));
                }});
    }

    private void loadValidPartnerSaverInputValues() {
        Fixture.of(PartnerSaverInputValues.class)
                .addTemplate("valid", new Rule() {{
                    add("partners", Fixture.from(PartnerBase.class).gimme(integer(1, 20), "valid"));
                }});
    }

    private void loadValidPartnerGetterByIdOutputValues() {
        Fixture.of(PartnerGetterByIdOutputValues.class)
                .addTemplate("valid", new Rule() {{
                    add("partner", Optional.of(Fixture.from(Partner.class).gimme("valid")));
                }});
    }
}

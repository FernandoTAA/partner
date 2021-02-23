package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.util.FixtureUtils;

import java.util.Optional;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomTestUtils.integer;

/**
 * Static method to use in {@link MainFixture} for {@link InputValuesFixture} to use in Partner Tests;
 */
public class InputValuesFixture {
    static void loadValidPartnerSaverInputValues() {
        Fixture.of(PartnerSaverInputValues.class)
                .addTemplate("valid", new Rule() {{
                    add("partners", function(() -> Fixture.from(PartnerBase.class).gimme(integer(1, 20), "valid")));
                }});
    }
}

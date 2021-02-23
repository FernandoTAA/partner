package com.github.fernandotaa.partner.test.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;

import java.util.Optional;

import static com.github.fernandotaa.partner.util.FixtureUtils.function;
import static com.github.fernandotaa.partner.util.RandomTestUtils.integer;

/**
 * Static method to use in {@link MainFixture} for {@link OutputValuesFixture} to use in Partner Tests;
 */
public class OutputValuesFixture {
    static void loadValidPartnerGetterByIdOutputValues() {
        Fixture.of(PartnerGetterPartnerOutputValues.class)
                .addTemplate("valid", new Rule() {{
                    add("partner", function(() -> Optional.of(Fixture.from(Partner.class).gimme("valid"))));
                }});
    }

    static void loadInvalidPartnerGetterByIdOutputValuesEmpty() {
        Fixture.of(PartnerGetterPartnerOutputValues.class)
                .addTemplate("empty", new Rule() {{
                    add("partner", Optional.empty());
                }});
    }
}

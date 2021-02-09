package com.github.fernandotaa.partner.test.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.entrypoint.rest.data.PartnerBatchRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(classes = PartnerSaverUseCase.class)
@DisplayName("Test cases of PartnerSaverUseCase")
public class PartnerSaverUseCaseTest {
    @Autowired
    PartnerSaverUseCase partnerSaverUseCase;

    @BeforeAll
    static void beforeEach() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerSaverInputValues partnerBatchRequest = Fixture.from(PartnerSaverInputValues.class).gimme("valid");
        partnerSaverUseCase.execute(partnerBatchRequest);
    }
}

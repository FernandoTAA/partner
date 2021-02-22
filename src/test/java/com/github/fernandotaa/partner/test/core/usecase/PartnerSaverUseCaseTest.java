package com.github.fernandotaa.partner.test.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverInputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverOutputValues;
import com.github.fernandotaa.partner.core.usecase.saver.PartnerSaverUseCase;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringJUnitConfig(classes = PartnerSaverUseCase.class)
@DisplayName("Test cases of PartnerSaverUseCase")
public class PartnerSaverUseCaseTest {
    @Autowired
    PartnerSaverUseCase partnerSaverUseCase;

    @MockBean
    PartnerRepository partnerRepository;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @BeforeEach
    void beforeEach() {
        when(partnerRepository.save(any(PartnerBase.class))).thenAnswer(invocation -> RandomTestUtils.uuid());
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerSaverInputValues input = Fixture.from(PartnerSaverInputValues.class).gimme("valid");
        var output = partnerSaverUseCase.execute(input);
        assertThat(output).isNotNull().extracting(PartnerSaverOutputValues::getPartnerIds).isNotNull().asList().hasSize(input.getPartners().size());
    }
}

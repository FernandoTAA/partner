package com.github.fernandotaa.partner.test.core.usecase;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdInputValues;
import com.github.fernandotaa.partner.core.usecase.getterbyid.PartnerGetterByIdUseCase;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringJUnitConfig(classes = PartnerGetterByIdUseCase.class)
@DisplayName("Test cases of PartnerSaverUseCase")
public class PartnerGetterByIdUseCaseTest {
    @Autowired
    PartnerGetterByIdUseCase partnerGetterByIdUseCase;

    @MockBean
    PartnerRepository partnerRepository;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerGetterPartnerOutputValues output = Fixture.from(PartnerGetterPartnerOutputValues.class).gimme("valid");
        var id = output.getPartner().get().getId();
        doReturn(output.getPartner()).when(partnerRepository).findById(id);
        var input = new PartnerGetterByIdInputValues(id);
        var found = partnerGetterByIdUseCase.execute(input);
        assertAll(
                () -> assertThat(found).isNotNull(),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getId).isNotNull(),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getTradingName).isEqualTo(output.getPartner().get().getTradingName()),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getOwnerName).isEqualTo(output.getPartner().get().getOwnerName()),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getDocument).isEqualTo(output.getPartner().get().getDocument()),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getCoverageArea).isEqualTo(output.getPartner().get().getCoverageArea()),
                () -> assertThat(found.getPartner()).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getAddress).isEqualTo(output.getPartner().get().getAddress())
        );
    }

    @Test
    @DisplayName("Error test case where there is not a record with this id")
    void notFound_error() {
        doReturn(Optional.empty()).when(partnerRepository).findById(anyString());
        var id = RandomTestUtils.uuid();
        var input = new PartnerGetterByIdInputValues(id);
        var notFound = partnerGetterByIdUseCase.execute(input);
        assertAll(
                () -> assertThat(notFound).isNotNull(),
                () -> assertThat(notFound.getPartner()).isNotNull().isEmpty()
        );
    }
}

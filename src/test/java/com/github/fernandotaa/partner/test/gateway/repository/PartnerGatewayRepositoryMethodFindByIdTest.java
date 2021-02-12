package com.github.fernandotaa.partner.test.gateway.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.PartnerGatewayRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomUtils;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.*;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = PartnerGatewayRepository.class)
@DisplayName("Test cases of PartnerGatewayRepository and method findById")
public class PartnerGatewayRepositoryMethodFindByIdTest {
    @Autowired
    PartnerGatewayRepository partnerGatewayRepository;

    @MockBean
    PartnerMongoDBRepository partnerMongoDBRepository;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerMongoDB partner = Fixture.from(PartnerMongoDB.class).gimme("saved");
        final String id = partner.getId();
        doReturn(Optional.of(partner)).when(partnerMongoDBRepository).findById(id);

        var found = partnerGatewayRepository.findById(id);
        Assertions.assertAll(
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getId).isNotNull().isEqualTo(partner.getId()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getTradingName).isNotNull().isEqualTo(partner.getTradingName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getOwnerName).isNotNull().isEqualTo(partner.getOwnerName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getDocument).isNotNull().isEqualTo(partner.getDocument())
        );
    }

    @Test
    @DisplayName("Error test case where there is not a record with this id")
    void notFound_error() {
        doReturn(Optional.empty()).when(partnerMongoDBRepository).findById(anyString());
        var id = RandomUtils.uuid();
        var notFound = partnerGatewayRepository.findById(id);
        assertThat(notFound).isNotNull().isEmpty();
    }
}

package com.github.fernandotaa.partner.test.gateway.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.gateway.repository.PartnerGatewayRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@SpringJUnitConfig(classes = PartnerGatewayRepository.class)
@DisplayName("Test cases of PartnerGatewayRepository and method findById")
public class PartnerGatewayRepositoryMethodFindByIdTest {
    @Autowired
    PartnerGatewayRepository partnerGatewayRepository;

    @MockBean
    PartnerMongoDBRepository partnerMongoDBRepository;

    @MockBean
    MongoTemplate mongoTemplate;

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
        assertAll(
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getId).isNotNull().isEqualTo(partner.getId()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getTradingName).isNotNull().isEqualTo(partner.getTradingName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getOwnerName).isNotNull().isEqualTo(partner.getOwnerName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getDocument).isNotNull().isEqualTo(partner.getDocument()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getCoverageArea).isNotNull().isEqualTo(new GeoJsonMultiPolygonAdapter(partner.getCoverageArea()).adapt()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().isNotNull().extracting(Partner::getAddress).isNotNull().isEqualTo(new GeoJsonPointAdapter(partner.getAddress()).adapt())
        );
    }

    @Test
    @DisplayName("Error test case where there is not a record with this id")
    void notFound_error() {
        doReturn(Optional.empty()).when(partnerMongoDBRepository).findById(anyString());
        var id = RandomTestUtils.uuid();
        var notFound = partnerGatewayRepository.findById(id);
        assertThat(notFound).isNotNull().isEmpty();
    }
}

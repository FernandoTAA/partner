package com.github.fernandotaa.partner.test.gateway.repository.mongodb;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Test cases of PartnerMongoDBRepository and method findById")
public class PartnerMongoDBRepositoryMethodFindByIdTest {
    @Autowired
    PartnerMongoDBRepository partnerMongoDBRepository;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerMongoDB partner = Fixture.from(PartnerMongoDB.class).gimme("valid");
        var saved = partnerMongoDBRepository.save(partner);
        var found = partnerMongoDBRepository.findById(saved.getId());
        assertAll(
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getId).isNotNull(),
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getTradingName).isEqualTo(partner.getTradingName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getOwnerName).isEqualTo(partner.getOwnerName()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getDocument).isEqualTo(partner.getDocument()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getCoverageArea).isEqualTo(partner.getCoverageArea()),
                () -> assertThat(found).isNotNull().isNotEmpty().get().extracting(PartnerMongoDB::getAddress).isEqualTo(partner.getAddress())
        );
    }

    @Test
    @DisplayName("Error test case where there is not a record with this id")
    void notFound_error() {
        var id = RandomTestUtils.uuid();
        var notFound = partnerMongoDBRepository.findById(id);
        assertThat(notFound).isNotNull().isEmpty();
    }
}

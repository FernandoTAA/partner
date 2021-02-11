package com.github.fernandotaa.partner.test.gateway.repository.mongodb;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Test cases of PartnerMongoDBRepository")
public class PartnerMongoDBRepositoryTest {
    @Autowired
    PartnerMongoDBRepository partnerMongoDBRepository;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        PartnerMongoDB partner = Fixture.from(PartnerMongoDB.class).gimme("valid");
        var result = partnerMongoDBRepository.save(partner);
        assertAll(
                () -> assertThat(result).isNotNull().extracting(PartnerMongoDB::getId).isNotNull(),
                () -> assertThat(result).isNotNull().extracting(PartnerMongoDB::getTradingName).isEqualTo(partner.getTradingName()),
                () -> assertThat(result).isNotNull().extracting(PartnerMongoDB::getOwnerName).isEqualTo(partner.getOwnerName()),
                () -> assertThat(result).isNotNull().extracting(PartnerMongoDB::getDocument).isEqualTo(partner.getDocument())
        );

    }
}

package com.github.fernandotaa.partner.test.gateway.repository.mongodb;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.util.RandomUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataMongoTest
@DisplayName("Test cases of PartnerMongoDBRepository and method findById")
public class PartnerMongoDBRepositoryMethodSaveTest {
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
        var saved = partnerMongoDBRepository.save(partner);
        assertAll(
                () -> assertThat(saved).isNotNull().extracting(PartnerMongoDB::getId).isNotNull(),
                () -> assertThat(saved).isNotNull().extracting(PartnerMongoDB::getTradingName).isEqualTo(partner.getTradingName()),
                () -> assertThat(saved).isNotNull().extracting(PartnerMongoDB::getOwnerName).isEqualTo(partner.getOwnerName()),
                () -> assertThat(saved).isNotNull().extracting(PartnerMongoDB::getDocument).isEqualTo(partner.getDocument())
        );
    }
}

package com.github.fernandotaa.partner.test.gateway.repository.mongodb;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class PartnerMongoDBRepositoryTest {
    @Autowired
    PartnerMongoDBRepository partnerMongoDBRepository;

    @BeforeAll
    static void beforeEach() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @Test
    void success() {
        PartnerMongoDB partner = Fixture.from(PartnerMongoDB.class).gimme("valid");
        var result = partnerMongoDBRepository.save(partner);
        org.junit.jupiter.api.Assertions.assertAll(
                () -> Assertions.assertThat(result).isNotNull().extracting(PartnerMongoDB::getId).isNotNull(),
                () -> Assertions.assertThat(result).isNotNull().extracting(PartnerMongoDB::getTradingName).isEqualTo(partner.getTradingName()),
                () -> Assertions.assertThat(result).isNotNull().extracting(PartnerMongoDB::getOwnerName).isEqualTo(partner.getOwnerName()),
                () -> Assertions.assertThat(result).isNotNull().extracting(PartnerMongoDB::getDocument).isEqualTo(partner.getDocument())
        );

    }
}

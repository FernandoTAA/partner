package com.github.fernandotaa.partner.test.gateway.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.PartnerGatewayRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DisplayName("Test cases of PartnerGatewayRepository and method findNearestInCoverageAreaByLocation")
public class PartnerGatewayRepositoryMethodFindNearestInCoverageAreaByLocationTest {
    PartnerGatewayRepository partnerGatewayRepository;

    @Autowired
    PartnerMongoDBRepository partnerMongoDBRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeAll
    static void beforeAll() {
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.fixture");
    }

    @BeforeEach
    void beforeEach() {
        partnerGatewayRepository = new PartnerGatewayRepository(partnerMongoDBRepository, mongoTemplate);
        List<PartnerBase> partners = Fixture.from(PartnerBase.class).gimme(3, "in_coverage_area");
        var mongoPartners = partners.stream().map(PartnerMongoDB::from).collect(Collectors.toList());
        partnerMongoDBRepository.saveAll(mongoPartners);
    }

    @Test
    @DisplayName("Success test case")
    void success() {
        GeoJsonPoint point = Fixture.from(GeoJsonPoint.class).gimme("in_coverage_area");
        var found = partnerGatewayRepository.findNearestInCoverageAreaByLocation(point);
        assertThat(found).isNotNull().isNotEmpty();
    }

    @Test
    @DisplayName("Error test case where it does not found Partner")
    void notFound_error() {
        GeoJsonPoint point = Fixture.from(GeoJsonPoint.class).gimme("out_of_coverage_area");
        var found = partnerGatewayRepository.findNearestInCoverageAreaByLocation(point);
        assertThat(found).isNotNull().isEmpty();
    }
}

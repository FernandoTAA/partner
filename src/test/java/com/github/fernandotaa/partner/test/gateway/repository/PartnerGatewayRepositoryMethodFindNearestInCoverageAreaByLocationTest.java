package com.github.fernandotaa.partner.test.gateway.repository;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.PartnerGatewayRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonMultiPolygonAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import com.github.fernandotaa.partner.util.RandomTestUtils;
import org.bson.Document;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

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
        FixtureFactoryLoader.loadTemplates("com.github.fernandotaa.partner.test.entrypoint.rest.data.fixture");
    }

    @BeforeEach
    void beforeEach() {
        partnerGatewayRepository = new PartnerGatewayRepository(partnerMongoDBRepository, mongoTemplate);
        List<PartnerBase> partners = Fixture.from(PartnerBase.class).gimme(3,"in_coverage_area");
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

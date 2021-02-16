package com.github.fernandotaa.partner.gateway.repository;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.adapter.GeoJsonPointMongoDBAdapter;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.NearQuery.near;

/**
 * Implementation of Repository to manage {@link PartnerBase} data.
 */
@Repository
@AllArgsConstructor
public class PartnerGatewayRepository implements PartnerRepository {
    private final PartnerMongoDBRepository partnerMongoDBRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public String save(PartnerBase partner) {
        var partnerMongoDB = PartnerMongoDB.from(partner);
        var saved = partnerMongoDBRepository.save(partnerMongoDB);
        return saved.getId();
    }

    @Override
    public Optional<Partner> findById(String id) {
        return partnerMongoDBRepository.findById(id).map(PartnerMongoDB::toEntity);
    }

    @Override
    public Optional<Partner> findNearestInCoverageAreaByLocation(GeoJsonPoint destination) {
        var point = new GeoJsonPointMongoDBAdapter(destination).adapt();

        var inclusionOfDistance = geoNear(near(point), "distance");
        var conditionOfInCoverageArea = match(where("coverageArea").intersects(point));
        var sortingByDistance = Aggregation.sort(Sort.Direction.DESC, "distance");
        var limitationToReturnOne = Aggregation.limit(1);

        var aggregation = newAggregation(inclusionOfDistance, conditionOfInCoverageArea, sortingByDistance, limitationToReturnOne);
        var output = mongoTemplate.aggregate(aggregation, "partner", PartnerMongoDB.class);
        var result = output.getMappedResults();
        return result.stream().findFirst().map(PartnerMongoDB::toEntity);
    }
}

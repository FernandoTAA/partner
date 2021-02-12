package com.github.fernandotaa.partner.gateway.repository;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation of Repository to manage {@link PartnerBase} data.
 */
@Repository
@AllArgsConstructor
public class PartnerGatewayRepository implements PartnerRepository {
    private final PartnerMongoDBRepository partnerMongoDBRepository;

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
}

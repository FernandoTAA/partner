package com.github.fernandotaa.partner.gateway.repository;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
}

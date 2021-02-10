package com.github.fernandotaa.partner.gateway.repository;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.gateway.repository.mongodb.PartnerMongoDBRepository;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Implementation of Repository to manage {@link Partner} data.
 */
@Repository
@AllArgsConstructor
public class PartnerGatewayRepository implements PartnerRepository {
    private final PartnerMongoDBRepository partnerMongoDBRepository;

    @Override
    public void save(Partner partner) {
        var partnerMongoDB = PartnerMongoDB.from(partner);
        partnerMongoDBRepository.save(partnerMongoDB);
    }
}

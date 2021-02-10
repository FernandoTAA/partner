package com.github.fernandotaa.partner.gateway.repository;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Partner} data in MongoDB.

 */
@Repository
public class PartnerMongoDBRepository implements PartnerRepository {
    @Override
    public void save(Partner partner) {

    }
}

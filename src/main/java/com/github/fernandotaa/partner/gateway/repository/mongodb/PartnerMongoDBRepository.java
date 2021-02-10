package com.github.fernandotaa.partner.gateway.repository.mongodb;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.gateway.repository.mongodb.data.PartnerMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage {@link Partner} data in MongoDB.
 */
@Repository
public interface PartnerMongoDBRepository extends MongoRepository<PartnerMongoDB, String> {
}

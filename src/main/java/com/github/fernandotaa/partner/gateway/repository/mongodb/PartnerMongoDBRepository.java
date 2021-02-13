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

//db.getCollection('partner').createIndex( { "address" : "2dsphere" } )

//db.getCollection('partner').aggregate([
//{
//    $geoNear: {
//        near: {
//            type: "Point",
//            coordinates: [ -43.297317, -23.013518]
//        },
//        distanceField: "distance.calculated",
//        spherical: true
//    }
//},
//{
//    $match : {
//        coverageArea: {
//            $geoIntersects: {
//                $geometry: {
//                    type: "Point",
//                    coordinates: [ -43.297337, -23.013538]
//                }
//            }
//        }
//    }
//},
//{$limit: 1}
//]);
}

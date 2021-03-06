package com.github.fernandotaa.partner.core.repository;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;

import java.util.Optional;

/**
 * Repository to manage {@link Partner} data.
 */
public interface PartnerRepository {

    /**
     * Save a new record of {@link Partner}.
     *
     * @param partner - {@link PartnerBase} to save.
     * @return - Id of saved {@link Partner}.
     */
    String save(PartnerBase partner);

    /**
     * Find record of {@link Partner} by id.
     *
     * @param id - Id to find {@link Partner}.
     * @return - {@link Partner} found.
     */
    Optional<Partner> findById(String id);

    /**
     * Return the nearest {@link Partner} in coverage area by {@link GeoJsonPoint}.
     *
     * @param destination - {@link GeoJsonPoint}
     * @return - List of @{@link Partner}
     */
    Optional<Partner> findNearestInCoverageAreaByLocation(GeoJsonPoint destination);
}

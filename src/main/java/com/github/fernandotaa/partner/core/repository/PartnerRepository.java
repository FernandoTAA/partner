package com.github.fernandotaa.partner.core.repository;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;

/**
 * Repository to manage {@link Partner} data.
 */
public interface PartnerRepository {

    /**
     * Save a new record of {@link Partner}.
     *
     * @param partner - {@link Partner} to save;
     */
    void save(Partner partner);
}

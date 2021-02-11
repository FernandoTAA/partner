package com.github.fernandotaa.partner.core.repository;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;

/**
 * Repository to manage {@link Partner} data.
 */
public interface PartnerRepository {

    /**
     * Save a new record of {@link Partner}.
     *
     * @param partner - {@link PartnerBase} to save;
     * @return
     */
    String save(PartnerBase partner);
}

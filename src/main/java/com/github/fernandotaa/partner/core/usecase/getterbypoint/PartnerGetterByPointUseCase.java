package com.github.fernandotaa.partner.core.usecase.getterbypoint;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.UseCase;
import lombok.AllArgsConstructor;

import javax.inject.Named;

/**
 * {@link UseCase} to get {@link com.github.fernandotaa.partner.core.usecase.entity.Partner} by {@link com.github.fernandotaa.partner.library.geojson.GeoJsonPoint}.
 */
@Named
@AllArgsConstructor
public class PartnerGetterByPointUseCase implements UseCase<PartnerGetterByPointInputValues, PartnerGetterPartnerOutputValues> {
    private PartnerRepository partnerRepository;

    @Override
    public PartnerGetterPartnerOutputValues execute(PartnerGetterByPointInputValues input) {
        var point = input.getPoint();
        var partner = partnerRepository.findNearestInCoverageAreaByLocation(point);
        return new PartnerGetterPartnerOutputValues(partner);
    }
}

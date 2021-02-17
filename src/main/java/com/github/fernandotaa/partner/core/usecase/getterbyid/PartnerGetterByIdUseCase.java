package com.github.fernandotaa.partner.core.usecase.getterbyid;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.PartnerGetterPartnerOutputValues;
import com.github.fernandotaa.partner.core.usecase.UseCase;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.Optional;

/**
 * {@link UseCase} to get {@link com.github.fernandotaa.partner.core.usecase.entity.Partner} by id.
 */
@Named
@AllArgsConstructor
public class PartnerGetterByIdUseCase implements UseCase<PartnerGetterByIdInputValues, PartnerGetterPartnerOutputValues> {
    private final PartnerRepository partnerRepository;

    @Override
    public PartnerGetterPartnerOutputValues execute(PartnerGetterByIdInputValues input) {
        Optional<Partner> partner = partnerRepository.findById(input.getPartnerId());
        return new PartnerGetterPartnerOutputValues(partner);
    }
}

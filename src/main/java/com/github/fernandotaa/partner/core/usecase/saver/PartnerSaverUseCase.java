package com.github.fernandotaa.partner.core.usecase.saver;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.EmptyOutputValues;
import com.github.fernandotaa.partner.core.usecase.UseCase;
import lombok.AllArgsConstructor;

import javax.inject.Named;


/**
 * {@link UseCase} to save {@link com.github.fernandotaa.partner.core.usecase.entity.Partner}.
 */
@Named
@AllArgsConstructor
public class PartnerSaverUseCase implements UseCase<PartnerSaverInputValues, EmptyOutputValues> {
    PartnerRepository partnerRepository;

    @Override
    public EmptyOutputValues execute(PartnerSaverInputValues input) {
        input.getPartners().forEach(partnerRepository::save);
        return EmptyOutputValues.instance();
    }
}

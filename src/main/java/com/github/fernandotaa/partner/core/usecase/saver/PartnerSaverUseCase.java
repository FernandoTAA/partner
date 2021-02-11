package com.github.fernandotaa.partner.core.usecase.saver;

import com.github.fernandotaa.partner.core.repository.PartnerRepository;
import com.github.fernandotaa.partner.core.usecase.UseCase;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.util.stream.Collectors;


/**
 * {@link UseCase} to save {@link com.github.fernandotaa.partner.core.usecase.entity.Partner}.
 */
@Named
@AllArgsConstructor
public class PartnerSaverUseCase implements UseCase<PartnerSaverInputValues, PartnerSaverOutputValues> {
    private PartnerRepository partnerRepository;

    @Override
    public PartnerSaverOutputValues execute(PartnerSaverInputValues input) {
        var parnterIds = input.getPartners().stream()
                .map(partnerRepository::save)
                .collect(Collectors.toList());
        return new PartnerSaverOutputValues(parnterIds);
    }
}

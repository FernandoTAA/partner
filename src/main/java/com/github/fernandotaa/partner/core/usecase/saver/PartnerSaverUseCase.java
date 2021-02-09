package com.github.fernandotaa.partner.core.usecase.saver;

import com.github.fernandotaa.partner.core.usecase.EmptyOutputValues;
import com.github.fernandotaa.partner.core.usecase.UseCase;

import javax.inject.Named;


/**
 * {@link UseCase} to save {@link com.github.fernandotaa.partner.core.usecase.entity.Partner}.
 */
@Named
public class PartnerSaverUseCase implements UseCase<PartnerSaverInputValues, EmptyOutputValues> {
    @Override
    public EmptyOutputValues execute(PartnerSaverInputValues input) {
        return null;
    }
}

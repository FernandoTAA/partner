package com.github.fernandotaa.partner.core.usecase.getterbyid;

import com.github.fernandotaa.partner.core.usecase.UseCase;

import javax.inject.Named;

/**
 * {@link UseCase} to get {@link com.github.fernandotaa.partner.core.usecase.entity.Partner} by id.
 */
@Named
public class PartnerGetterByIdUseCase implements UseCase<PartnerGetterByIdInputValues, PartnerGetterByIdOutputValues> {
    @Override
    public PartnerGetterByIdOutputValues execute(PartnerGetterByIdInputValues input) {
        return null;
    }
}

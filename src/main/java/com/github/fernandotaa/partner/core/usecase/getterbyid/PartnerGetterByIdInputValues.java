package com.github.fernandotaa.partner.core.usecase.getterbyid;

import com.github.fernandotaa.partner.core.usecase.InputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link InputValues} for {@link PartnerGetterByIdUseCase}.
 */
@Getter
@AllArgsConstructor
public class PartnerGetterByIdInputValues implements InputValues {
    private String partnerId;
}

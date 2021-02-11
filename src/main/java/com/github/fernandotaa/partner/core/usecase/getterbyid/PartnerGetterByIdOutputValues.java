package com.github.fernandotaa.partner.core.usecase.getterbyid;

import com.github.fernandotaa.partner.core.usecase.OutputValues;
import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * {@link OutputValues} for {@link PartnerGetterByIdUseCase}
 */
@Getter
@AllArgsConstructor
public class PartnerGetterByIdOutputValues implements OutputValues {
    private Optional<Partner> partner;
}

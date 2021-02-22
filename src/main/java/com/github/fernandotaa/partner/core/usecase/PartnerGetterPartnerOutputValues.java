package com.github.fernandotaa.partner.core.usecase;

import com.github.fernandotaa.partner.core.usecase.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * {@link OutputValues} for {@link PartnerGetterPartnerOutputValues}
 */
@Getter
@AllArgsConstructor
public class PartnerGetterPartnerOutputValues implements OutputValues {
    private Optional<Partner> partner;
}

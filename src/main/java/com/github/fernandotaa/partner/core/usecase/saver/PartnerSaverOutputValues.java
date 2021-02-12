package com.github.fernandotaa.partner.core.usecase.saver;

import com.github.fernandotaa.partner.core.usecase.OutputValues;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * {@link OutputValues} for {@link PartnerSaverUseCase}.
 */
@Getter
@AllArgsConstructor
final public class PartnerSaverOutputValues implements OutputValues {
    private List<String> partnerIds;
}

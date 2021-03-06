package com.github.fernandotaa.partner.core.usecase.saver;

import com.github.fernandotaa.partner.core.usecase.InputValues;
import com.github.fernandotaa.partner.core.usecase.entity.PartnerBase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * {@link InputValues} for {@link PartnerSaverUseCase}.
 */
@Getter
@AllArgsConstructor
public class PartnerSaverInputValues implements InputValues {
    private List<PartnerBase> partners;
}

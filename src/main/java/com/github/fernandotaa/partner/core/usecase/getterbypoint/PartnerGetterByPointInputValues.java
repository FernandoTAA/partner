package com.github.fernandotaa.partner.core.usecase.getterbypoint;

import com.github.fernandotaa.partner.core.usecase.InputValues;
import com.github.fernandotaa.partner.library.geojson.GeoJsonPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@link InputValues} for {@link PartnerGetterByPointUseCase}
 */
@Getter
@AllArgsConstructor
public class PartnerGetterByPointInputValues implements InputValues {
    private GeoJsonPoint point;
}

package com.github.fernandotaa.partner.core.usecase;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * {@link InputValues} for empty result.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final public class EmptyOutputValues implements OutputValues {
    private final static EmptyOutputValues INSTANCE = new EmptyOutputValues();

    public static EmptyOutputValues instance() {
        return INSTANCE;
    }
}

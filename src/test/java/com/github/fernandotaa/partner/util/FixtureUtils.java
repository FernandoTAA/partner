package com.github.fernandotaa.partner.util;

import br.com.six2six.fixturefactory.function.AtomicFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * Utility with static methods for Fixture
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final public class FixtureUtils {
    /**
     * Convert {@link Supplier} to {@link AtomicFunction}.
     *
     * @param supplier - {@link Supplier}
     * @return - {@link AtomicFunction}
     */
    @SuppressWarnings("unchecked")
    public static AtomicFunction function(Supplier<Object> supplier) {
        return new AtomicFunction() {
            @Override
            public Object generateValue() {
                return supplier.get();
            }
        };
    }
}

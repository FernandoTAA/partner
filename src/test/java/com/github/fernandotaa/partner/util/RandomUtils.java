package com.github.fernandotaa.partner.util;

import br.com.six2six.fixturefactory.function.impl.CnpjFunction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Utility with static methods for random actions.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final public class RandomUtils {

    /**
     * Generate a random integer obeying min and max rule.
     *
     * @param min - Minimum value to return
     * @param max - Maximum value to return
     * @return - Random integer value
     */
    public static Integer integer(Integer min, Integer max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    /**
     * Generate a random document with pattern "{number}/{number}"
     * Example: 02.453.716/000170
     *          04.433.714/0001-44
     *
     * @return
     */
    public static String document() {
        return new CnpjFunction(true).generateValue();
    }
}

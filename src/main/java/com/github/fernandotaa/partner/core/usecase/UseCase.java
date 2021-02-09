package com.github.fernandotaa.partner.core.usecase;

/**
 * Contract of use case.
 *
 * @param <T> - {@link InputValues}
 * @param <R> - {@link OutputValues}
 */
public interface UseCase<T extends InputValues, R extends OutputValues> {
    /**
     * Method to execute use case.
     *
     * @param input - {@link InputValues}
     * @return - {@link OutputValues}
     */
    R execute(T input);
}

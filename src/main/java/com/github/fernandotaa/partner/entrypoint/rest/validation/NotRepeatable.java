package com.github.fernandotaa.partner.entrypoint.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Annotation to avoid repeatable value in {@link java.util.Collection}.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotRepeatableValidator.class)
public @interface NotRepeatable {
    /**
     * Message to return in case of repeatable value. <br />
     * This message has just one parameter with index 0.
     *
     * @return - Message
     */
    String message() default "must not be repeatable values: {0}";

    /**
     * Field inside of object in {@link java.util.Collection} validated.
     *
     * @return - Field
     */
    String field();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

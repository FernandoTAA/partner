package com.github.fernandotaa.partner.entrypoint.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotRepeatableValidator.class)
public @interface NotRepeatable {
    String message() default "must not be repeatable values: {0}";
    String field();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

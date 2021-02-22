package com.github.fernandotaa.partner.entrypoint.rest.validation;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

/**
 * Validator to avoid repeatable value in {@link Collection}.
 */
public class NotRepeatableValidator implements ConstraintValidator<NotRepeatable, Collection<?>> {
    private String field;
    private String message;

    @Override
    public void initialize(NotRepeatable constraintAnnotation) {
        field = constraintAnnotation.field();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Collection<?> collection, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(collection)) {
            return true;
        }

        var quantityOfDuplicatedValues = getSetWithDuplicatedValues(collection).size();

        var repeatable = quantityOfDuplicatedValues > 0;

        if (repeatable) {
            var fields = String.join(",", getSetWithDuplicatedValues(collection));
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(format(message, fields)).addConstraintViolation();
        }

        return !repeatable;
    }

    private Set<String> getSetWithDuplicatedValues(Collection<?> collection) {
        var fieldList = collection.stream()
                .map(this::getProperty).collect(Collectors.toList());
        return fieldList.stream()
                .filter(fieldValue -> Collections.frequency(fieldList, fieldValue) > 1)
                .collect(Collectors.toSet());
    }

    @SneakyThrows
    private String getProperty(Object object) {
        return BeanUtils.getProperty(object, field);
    }
}

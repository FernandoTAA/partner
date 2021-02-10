package com.github.fernandotaa.partner.test.entrypoint.rest.validation;

import com.github.fernandotaa.partner.entrypoint.rest.validation.NotRepeatable;
import com.github.fernandotaa.partner.entrypoint.rest.validation.NotRepeatableValidator;
import com.github.fernandotaa.partner.util.RandomUtils;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.ListUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@DisplayName("Test cases NotRepeatableValidator")
public class NotRepeatableValidatorTest {
    @Getter
    @AllArgsConstructor
    public static class ObjectTest {
        private String property;
    }

    @Test
    @DisplayName("Test case with no repeated value")
    void success() {
        var validator = new NotRepeatableValidator() {{
            initialize(generateNotRepeatableAnnotation("must not be repeatable values: {0}", "property"));
        }};

        var constraintValidatorContext = getMockConstraintValidatorContext();

        var valid = validator.isValid(getCollectionTest(), constraintValidatorContext);

        Assertions.assertThat(valid).isTrue();
    }

    @Test
    @DisplayName("Test case with repeated value")
    void duplicated() {
        var validator = new NotRepeatableValidator() {{
            initialize(generateNotRepeatableAnnotation("must not be repeatable values: {0}", "property"));
        }};

        var constraintValidatorContext = getMockConstraintValidatorContext();

        var valid = validator.isValid(getCollectionTestWithDuplicatedProperty(), constraintValidatorContext);

        Assertions.assertThat(valid).isFalse();
    }

    @Test
    @DisplayName("Test case with wrong field")
    void wrongField() {
        var validator = new NotRepeatableValidator() {{
            initialize(generateNotRepeatableAnnotation("must not be repeatable values: {0}", "property1"));
        }};

        var constraintValidatorContext = getMockConstraintValidatorContext();

        org.junit.jupiter.api.Assertions.assertThrows(NoSuchMethodException.class, () -> validator.isValid(getCollectionTestWithDuplicatedProperty(), constraintValidatorContext));
    }

    private List<ObjectTest> getCollectionTest() {
        return Stream.generate(Faker.instance().name()::name)
                .distinct()
                .limit(RandomUtils.integer(2, 20))
                .map(ObjectTest::new)
                .collect(Collectors.toList());
    }

    private List<ObjectTest> getCollectionTestWithDuplicatedProperty() {
        var collectionTest = getCollectionTest();
        return ListUtils.union(collectionTest, List.of(collectionTest.get(0)));
    }

    private ConstraintValidatorContext getMockConstraintValidatorContext() {
        var mock = mock(ConstraintValidatorContext.class);
        doReturn(mock(ConstraintValidatorContext.ConstraintViolationBuilder.class)).when(mock).buildConstraintViolationWithTemplate(anyString());
        return mock;
    }

    private NotRepeatable generateNotRepeatableAnnotation(String message, String field) {
        return new NotRepeatable() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return this.getClass();
            }

            @Override
            public String message() {
                return message;
            }

            @Override
            public String field() {
                return field;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }
        };
    }
}


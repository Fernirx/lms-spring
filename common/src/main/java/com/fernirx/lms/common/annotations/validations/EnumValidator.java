package com.fernirx.lms.common.annotations.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValidator implements ConstraintValidator<ValidEnum, String> {
    private List<String> enumValues;
    private boolean allowNull;
    private boolean ignoreCase;

    @Override
    public void initialize(ValidEnum annotation) {
        ignoreCase = annotation.ignoreCase();
        allowNull = annotation.allowNull();
        Enum<?>[] enums = annotation.enumClass().getEnumConstants();
        enumValues = Arrays.stream(enums)
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return allowNull;
        return ignoreCase
                ? enumValues.stream().anyMatch(e -> e.equalsIgnoreCase(value))
                : enumValues.contains(value);
    }
}

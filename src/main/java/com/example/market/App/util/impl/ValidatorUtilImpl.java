package com.example.market.App.util.impl;

import com.example.market.App.util.ValidatorUtil;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.DateFormatter;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil {

    private final Validator validator;

    @Autowired
    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }

    @Override
    public <E> Set<ConstraintViolation<E>> violation(E entity) {
        return this.validator.validate(entity);
    }

    @Override
    public Object isValidDto(Object object) {
        StringBuilder sb = new StringBuilder();

        if(!isValid(object)){
           violation(object)
                    .forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));

            return sb.toString();
        }
        return object;
    }

    @Override
    public boolean isValidDate(String date) {
        return GenericValidator.isDate(date, "yyyy-MM-dd", true);
    }
}

package com.example.market.App.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> violation(E entity);

    boolean isValidDate(String date);

    Object isValidDto(Object object);
}

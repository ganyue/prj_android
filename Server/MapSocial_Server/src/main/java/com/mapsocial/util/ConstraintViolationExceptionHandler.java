package com.mapsocial.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue.gan on 2017/10/16.
 */
public class ConstraintViolationExceptionHandler {

    public static String getMessage (ConstraintViolationException e) {
        List<String> msgs = new ArrayList<>();
        for (ConstraintViolation constraintViolation :e.getConstraintViolations()) {
            msgs.add(constraintViolation.getMessage());
        }

        return StringUtils.join(msgs, ';');
    }
}

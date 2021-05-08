/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author oghomwen.aigbedion
 */
@Component
public class RequestValidator {

    public List<String> validate(Object validatable) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(validatable);

        List<String> messages = new ArrayList<>();

        if (!violations.isEmpty()) {
            violations.forEach((violation) -> {
                messages.add(violation.getMessage());
            });
        }
        return messages;
    }
}

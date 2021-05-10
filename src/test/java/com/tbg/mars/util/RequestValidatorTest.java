/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.util;

import com.tbg.mars.exception.CustomException;
import com.tbg.mars.request.UnitReviewRequest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author oghomwen.aigbedion
 */
@SpringBootTest
public class RequestValidatorTest {

    @Autowired
    private RequestValidator requestValidator;

    @Test
    public void itShouldThrowException_WhenInvalidValueEnteredForField() {

        UnitReviewRequest request = new UnitReviewRequest();
        request.setScore(6);

        Exception exception = assertThrows(CustomException.class, () -> {
            requestValidator.validateRequest(request);
        });

        String expectedMessage = "[score should be at most 5]";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

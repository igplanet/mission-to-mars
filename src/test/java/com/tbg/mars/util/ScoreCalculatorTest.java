/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.util;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author oghomwen.aigbedion
 */
@SpringBootTest
public class ScoreCalculatorTest {

    @Autowired
    private ScoreCalculator scoreCalculator;

    @Test
    public void itShouldReturnAverageNewScore_WhenTwoScoresAreEntered() {

        //given
        BigDecimal currentScore = new BigDecimal(5);
        Integer reviewScore = 4;
        BigDecimal expectedScore = new BigDecimal(4.5);

        //when
        BigDecimal newScore = scoreCalculator.getNewScore(currentScore, reviewScore);

        //then
        assertTrue(expectedScore.compareTo(newScore) == 0);
    }

}

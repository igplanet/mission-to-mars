/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author oghomwen.aigbedion
 */
@Component
public class ScoreCalculator {

    public BigDecimal getNewScore(BigDecimal currentScore, Integer reviewScore) {
        BigDecimal newScore;
        if (currentScore != null) {
            Double score = (currentScore.doubleValue() + reviewScore) / 2;
            newScore = new BigDecimal(score).setScale(1, RoundingMode.HALF_UP);
            return newScore;
        } else {
            newScore = new BigDecimal(reviewScore);
            return newScore;
        }

    }

}

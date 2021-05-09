/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Data
public class UnitReviewRequest {

    @Min(value = 1, message = "score should be atleast 1")
    @Max(value = 5, message = "score should be at most 5")
    private Integer score;
    @Size(max = 1000, message = "comments should not be more than 1000 characters")
    private String comment;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Entity
@Data
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 400)
    private String image;
    private String title;
    private String region;
    private String description;
    private String cancellationPolicy;
    private BigDecimal price;
    @Min(value = 1)
    @Max(value = 5)
    private Integer Score;

}

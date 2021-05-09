/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Size(max = 1000)
    private String description;
    @Size(max = 1000)
    private String cancellationPolicy;
    @Column(scale = 2)
    private BigDecimal price;
    @Column(scale = 1)
    private BigDecimal Score;

}

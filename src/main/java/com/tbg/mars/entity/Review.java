/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Unit unit;
    @ManyToOne
    private Colonist colonist;
    private Integer score;
    @Size(max = 1000)
    private String comment;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date entryDate;

}

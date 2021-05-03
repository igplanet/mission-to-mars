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
import javax.persistence.Temporal;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Entity
@Data
public class Colonist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String colonistId;
    private String password;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOfRegistration;

}

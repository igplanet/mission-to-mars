/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tbg.mars.security.Role;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    @Column(unique = true)
    private String colonistId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String password;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOfRegistration;
    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

}

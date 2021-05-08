/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.request;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Data
public class UserAuthenticationRequest {

    @NotEmpty(message = "Please enter an Id")
    private String colonistId;
    @NotEmpty(message = "Please enter a password")
    private String password;

}

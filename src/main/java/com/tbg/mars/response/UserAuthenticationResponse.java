/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.response;

import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Data
public class UserAuthenticationResponse {

    private int responseCode;
    private String responseMessage;
    private String token;
}

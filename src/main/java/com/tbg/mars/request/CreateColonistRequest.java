/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.request;

import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author oghomwen.aigbedion
 */
@Data
public class CreateColonistRequest {

    @Size(min = 4, message = "Id should be at least 4 characters")
    private String colonistId;
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;
}

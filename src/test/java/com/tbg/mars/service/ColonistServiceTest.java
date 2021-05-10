/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.request.CreateColonistRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author oghomwen.aigbedion
 */
@SpringBootTest
public class ColonistServiceTest {

    @Autowired
    private ColonistService colonistService;

    @Test
    public void whenNewColonistCreated_ShouldReturnJWTToken() {
        //given
        CreateColonistRequest request = new CreateColonistRequest();
        request.setColonistId("colonist");
        request.setPassword("password");

        //when
        String token = colonistService.createColonist(request);

        //then
        assertTrue(token.length() > 0);
    }

    @Test
    public void whenLoginCredentialsAreValid_ShouldReturnJWTToken() {
        //given
        String colonistId = "colonist";
        String password = "password";

        //when
        String token = colonistService.authenticateColonist(colonistId, password);

        //then
        assertTrue(token.length() > 0);
    }

    @Test
    public void whenColonistWasLoggedIn_ShouldReturnRefreshedJWTToken() {
        //given
        String colonistId = "colonist";

        //when
        String token = colonistService.refresh(colonistId);

        //then
        assertTrue(token.length() > 0);
    }

}

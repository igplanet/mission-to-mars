/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbg.mars.MissionToMarsApplication;
import com.tbg.mars.request.CreateColonistRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author oghomwen.aigbedion
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MissionToMarsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ColonistControllerTest {

    @Autowired
    private MockMvc mvc;

    private String token = "";

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    public void whenSignUpCredentialsAreValid_ThenStatusIsOk() throws Exception {

        //given
        CreateColonistRequest request = new CreateColonistRequest();
        request.setColonistId("colonistnew");
        request.setPassword("password");

        mvc.perform(post("/colonist/signup")
                .content(MAPPER.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void whenSignUpCredentialsAreInValid_ThenStatusIsUnprocessableEntity() throws Exception {

        //given
        CreateColonistRequest request = new CreateColonistRequest();
        request.setColonistId("col");
        request.setPassword("password");

        mvc.perform(post("/colonist/signup")
                .content(MAPPER.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void whenSignInCredentialsAreValid_ThenStatusIsOk_AndTokenRefreshIsOk() throws Exception {

        //given
        String request = "colonistId=colonistnew&password=password";

        mvc.perform(post("/colonist/signin")
                .content(request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andDo((mr) -> {
                    token = mr.getResponse().getContentAsString();
                })
                .andExpect(status().isOk());

        mvc.perform(get("/colonist/refresh")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());

    }
}

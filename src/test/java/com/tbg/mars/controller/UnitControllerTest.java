/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.MissionToMarsApplication;
import com.tbg.mars.request.CreateColonistRequest;
import com.tbg.mars.service.ColonistService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UnitControllerTest {

    @Autowired
    private MockMvc mvc;

    private String token = "";

    @Autowired
    private ColonistService colonistService;

    @BeforeAll
    void init() {
        CreateColonistRequest request = new CreateColonistRequest();
        request.setColonistId("newcolonist1");
        request.setPassword("password");

        token = colonistService.createColonist(request);
    }

    @Test
    public void whenNoParamtersAreSupplied_ThenStatusIsOk() throws Exception {

        mvc.perform(get("/unit/units")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }

    @Test
    public void whenInputParamtersAreValid_ThenStatusIsOk() throws Exception {

        //given
        String request = "page=0&size=5&sortDirection=ASC&sortName=PRICE";

        mvc.perform(get("/unit/units")
                .header("Authorization", "Bearer " + token)
                .content(request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());

    }

    @Test
    public void whenSearchParamtersAreValid_ThenStatusIsOk() throws Exception {

        //given
        String request = "page=0&size=5&sortDirection=ASC&sortName=PRICE&searchParamter=TITLE&searchValue=";

        mvc.perform(post("/unit/search")
                .header("Authorization", "Bearer " + token)
                .content(request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isOk());

    }
}

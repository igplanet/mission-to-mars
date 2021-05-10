/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbg.mars.MissionToMarsApplication;
import com.tbg.mars.entity.Unit;
import com.tbg.mars.repo.UnitRepo;
import com.tbg.mars.request.CreateColonistRequest;
import com.tbg.mars.request.UnitReviewRequest;
import com.tbg.mars.service.ColonistService;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    private String token;

    private final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private ColonistService colonistService;

    @Autowired
    private UnitRepo repo;

    private Unit unit;

    @BeforeAll
    void init() {
        CreateColonistRequest request = new CreateColonistRequest();
        request.setColonistId("newcolonist");
        request.setPassword("password");

        token = colonistService.createColonist(request);

        unit = new Unit();
        unit = repo.save(unit);

    }

    @Test
    public void whenReviewIsSuccessful_ThenStatusIsOk_AndShouldReturnThankYouMessage() throws Exception {

        //given
        UnitReviewRequest request = new UnitReviewRequest();
        request.setComment("This is a great place");
        request.setScore(4);

        mvc.perform(post("/review/".concat(unit.getId() + ""))
                .header("Authorization", "Bearer " + token)
                .content(MAPPER.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect((mr) -> {
                    assertEquals("Thank you for the review", mr.getResponse().getContentAsString());
                });

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.entity.Unit;
import com.tbg.mars.repo.UnitRepo;
import com.tbg.mars.request.CreateColonistRequest;
import com.tbg.mars.request.UnitReviewRequest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author oghomwen.aigbedion
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class UnitReviewServiceTest {

    @Autowired
    private UnitReviewService reviewService;

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

        colonistService.createColonist(request);

        unit = new Unit();
        unit = repo.save(unit);
    }

    @Test
    public void whenReviewIsSuccessful_ShouldReturnThankYouMessage() {
        //given
        UnitReviewRequest request = new UnitReviewRequest();
        request.setComment("This is a great place");
        request.setScore(4);

        String expected = "Thank you for the review";

        //when
        String actual = reviewService.addReview("newcolonist", unit.getId(), request);

        //then
        assertTrue(expected.contains(actual));

    }

}

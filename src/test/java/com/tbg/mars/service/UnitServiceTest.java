/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.entity.Unit;
import com.tbg.mars.util.UnitProperty;
import com.tbg.mars.util.UnitSearchProperty;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 *
 * @author oghomwen.aigbedion
 */
@SpringBootTest
public class UnitServiceTest {

    @Autowired
    private UnitService service;

    @Test
    public void shouldReturn5Records_WhenNoParamtersAreProvided() {
        //given
        Optional<Integer> page = Optional.empty();
        Optional<Integer> size = Optional.empty();
        Optional<Sort.Direction> direction = Optional.empty();
        Optional<UnitProperty> sortName = Optional.empty();

        //when
        Page<Unit> unitsPage = service.getUnits(page, size, direction, sortName);

        //then
        assertFalse(unitsPage.isEmpty());
        assertTrue(unitsPage.getSize() == 5);

    }

    @Test
    public void contentShouldNotBeEmpty_WhenSearchIsSuccessful() {
        //given
        Optional<Integer> page = Optional.empty();
        Optional<Integer> size = Optional.empty();
        Optional<Sort.Direction> direction = Optional.of(Sort.Direction.ASC);
        Optional<UnitProperty> sortName = Optional.of(UnitProperty.TITLE);
        UnitSearchProperty searchParamter = UnitSearchProperty.REGION;
        String searchParamterValue = "";

        //when
        Page<Unit> unitsPage = service.searchUnits(page, size, direction, searchParamter, searchParamterValue, sortName);

        //then
        assertFalse(unitsPage.isEmpty());
    }

}

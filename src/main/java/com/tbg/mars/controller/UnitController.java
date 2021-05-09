/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.entity.Unit;
import com.tbg.mars.service.UnitService;
import com.tbg.mars.util.UnitProperty;
import com.tbg.mars.util.UnitSearchProperty;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oghomwen.aigbedion
 */
@RestController
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/units")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Page<Unit> getUnits(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortDirection") Optional<Sort.Direction> direction,
            @RequestParam("sortName") Optional<UnitProperty> sortName) {

        return unitService.getUnits(page, size, direction, sortName);
    }

    @PostMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public Page<Unit> searchUnits(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortDirection") Optional<Sort.Direction> direction,
            @RequestParam("sortName") Optional<UnitProperty> sortName,
            @RequestParam("searchParamter") UnitSearchProperty searchParamter,
            @RequestParam("searchValue") String searchParamterValue
    ) {

        return unitService.searchUnits(page, size, direction, searchParamter, searchParamterValue, sortName);
    }

}

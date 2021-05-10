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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
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
@Api(tags = "Unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("/units")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    @ApiOperation(value = "Allows for infinite listing of Units to be implemented in the browser", response = Page.class, authorizations = {
        @Authorization(value = "apiKey")})
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Something went wrong")
        ,
    @ApiResponse(code = 403, message = "Access denied")
        ,
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
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
    @ApiOperation(value = "Allow searching for units based on title or region and sorting by score", response = Page.class, authorizations = {
        @Authorization(value = "apiKey")})
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Something went wrong")
        ,
    @ApiResponse(code = 403, message = "Access denied")
        ,
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
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

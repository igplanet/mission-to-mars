/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.service.ColonistService;
import com.tbg.mars.request.CreateColonistRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oghomwen.aigbedion
 */
@RestController
@RequestMapping("/colonist")
@Api(tags = "Colonist")
public class ColonistController {

    @Autowired
    private ColonistService colonistService;

    @PostMapping(value = "/signin")
    @ResponseBody
    @ApiOperation(value = "Authenticates user and returns a JWT token")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Something went wrong")
        ,
        @ApiResponse(code = 422, message = "Incorrect login credentials")})
    public String authenticate(
            @RequestParam String colonistId,
            @RequestParam String password) {
        return colonistService.authenticateColonist(colonistId, password);
    }

    @PostMapping(value = "/signup")
    @ResponseBody
    @ApiOperation(value = "Creates user and returns a JWT token")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Something went wrong")
        ,
        @ApiResponse(code = 422, message = "Please choose another Id")})
    public String createColonist(
            @RequestBody CreateColonistRequest createColonistRequest) {
        return colonistService.createColonist(createColonistRequest);
    }

    @GetMapping(value = "/refresh")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "Returns a new JWT for a previously logged in user")
    public String refresh(HttpServletRequest req) {
        return colonistService.refresh(req.getRemoteUser());
    }
}

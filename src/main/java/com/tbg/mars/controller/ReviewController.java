/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.request.UnitReviewRequest;
import com.tbg.mars.service.UnitReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author oghomwen.aigbedion
 */
@RestController
@RequestMapping("/review")
@Api(tags = "Review")
public class ReviewController {

    @Autowired
    private UnitReviewService unitReviewService;

    @PostMapping("{unitId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    @ApiOperation(value = "Adds a review for a Unit", response = ResponseEntity.class, authorizations = {
        @Authorization(value = "apiKey")})
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Something went wrong")
        ,
    @ApiResponse(code = 403, message = "Access denied")
        ,
    @ApiResponse(code = 404, message = "Unit not found")
        ,
    @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<String> addReview(
            HttpServletRequest req,
            @PathVariable Long unitId,
            @RequestBody UnitReviewRequest unitReviewRequest) {
        String message = unitReviewService.addReview(req.getRemoteUser(), unitId, unitReviewRequest);
        return ResponseEntity.ok(message);
    }

}

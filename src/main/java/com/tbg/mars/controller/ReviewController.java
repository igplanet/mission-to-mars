/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.request.UnitReviewRequest;
import com.tbg.mars.service.UnitReviewService;
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
public class ReviewController {

    @Autowired
    private UnitReviewService unitReviewService;

    @PostMapping("{unitId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public ResponseEntity<String> addReview(
            HttpServletRequest req,
            @PathVariable Long unitId,
            @RequestBody UnitReviewRequest unitReviewRequest) {
        String message = unitReviewService.addReview(req.getRemoteUser(), unitId, unitReviewRequest);
        return ResponseEntity.ok(message);
    }

}

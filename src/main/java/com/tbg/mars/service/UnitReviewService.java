/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.entity.Colonist;
import com.tbg.mars.entity.Review;
import com.tbg.mars.entity.Unit;
import com.tbg.mars.exception.CustomException;
import com.tbg.mars.repo.ColonistRepo;
import com.tbg.mars.repo.ReviewRepo;
import com.tbg.mars.repo.UnitRepo;
import com.tbg.mars.request.UnitReviewRequest;
import com.tbg.mars.util.RequestValidator;
import com.tbg.mars.util.ScoreCalculator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author oghomwen.aigbedion
 */
@Service
public class UnitReviewService {

    @Autowired
    private ColonistRepo colonistRepo;

    @Autowired
    private UnitRepo unitRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private RequestValidator validator;

    @Autowired
    private ScoreCalculator scoreCalculator;

    public String addReview(String colonistId, Long unitId, UnitReviewRequest unitReviewRequest) {
        Colonist colonist = colonistRepo.findByColonistId(colonistId);
        if (colonist == null) {
            throw new CustomException("Colonist not found", HttpStatus.BAD_REQUEST);
        }

        Optional<Unit> optionalUnit = unitRepo.findById(unitId);
        if (!optionalUnit.isPresent()) {
            throw new CustomException("Unit not found", HttpStatus.NOT_FOUND);
        }

        Unit unit = optionalUnit.get();

        validator.validateRequest(unitReviewRequest);

        Review review = new Review();
        review.setColonist(colonist);
        review.setUnit(unit);
        review.setEntryDate(new Date());
        review.setScore(unitReviewRequest.getScore());
        review.setComment(unitReviewRequest.getComment());

        reviewRepo.save(review);

        BigDecimal newUnitScore = scoreCalculator.getNewScore(unit.getScore(), unitReviewRequest.getScore());
        unit.setScore(newUnitScore);

        unitRepo.save(unit);

        return "Thank you for the review";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.repo;

import com.tbg.mars.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oghomwen.aigbedion
 */
public interface ReviewRepo extends JpaRepository<Review, Long> {

}

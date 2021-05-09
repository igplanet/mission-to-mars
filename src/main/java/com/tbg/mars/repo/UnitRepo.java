/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.repo;

import com.tbg.mars.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author oghomwen.aigbedion
 */
public interface UnitRepo extends JpaRepository<Unit, Long> {

    Page<Unit> findByTitleContainingIgnoreCase(String infix, Pageable pageable);

    Page<Unit> findByRegionContainingIgnoreCase(String infix, Pageable pageable);

}

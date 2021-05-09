/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.entity.Unit;
import com.tbg.mars.repo.UnitRepo;
import com.tbg.mars.util.UnitProperty;
import com.tbg.mars.util.UnitSearchProperty;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author oghomwen.aigbedion
 */
@Service
public class UnitService {

    @Autowired
    private UnitRepo unitRepo;

    public Page<Unit> getUnits(Optional<Integer> page, Optional<Integer> size, Optional<Sort.Direction> direction, Optional<UnitProperty> sortName) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);

        Pageable pageable;

        if (direction.isPresent() && sortName.isPresent()) {
            Sort sortOrder = Sort.by(direction.get(), sortName.get().getName());
            pageable = PageRequest.of(currentPage, pageSize, sortOrder);
        } else {
            pageable = PageRequest.of(currentPage, pageSize);
        }

        Page<Unit> unitsPage = unitRepo.findAll(pageable);

        return unitsPage;
    }

    public Page<Unit> searchUnits(
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<Sort.Direction> direction,
            UnitSearchProperty searchParamter,
            String searchParamterValue,
            Optional<UnitProperty> sortName) {

        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        Sort.Direction sortDirection = direction.orElse(Sort.Direction.DESC);
        UnitProperty sortColumn = sortName.orElse(UnitProperty.SCORE);

        Sort sortOrder = Sort.by(sortDirection, sortColumn.getName());
        Pageable pageable = PageRequest.of(currentPage, pageSize, sortOrder);

        Page<Unit> unitsPage;
        if (searchParamter.equals(UnitSearchProperty.REGION)) {
            unitsPage = unitRepo.findByRegionContainingIgnoreCase(searchParamterValue, pageable);
        } else {
            unitsPage = unitRepo.findByTitleContainingIgnoreCase(searchParamterValue, pageable);
        }

        return unitsPage;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.service;

import com.tbg.mars.entity.Colonist;
import com.tbg.mars.exception.CustomException;
import com.tbg.mars.repo.ColonistRepo;
import com.tbg.mars.request.CreateColonistRequest;
import com.tbg.mars.security.JwtTokenProvider;
import com.tbg.mars.security.Role;
import com.tbg.mars.util.RequestValidator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author oghomwen.aigbedion
 */
@Service
public class ColonistService {

    @Autowired
    private ColonistRepo colonistRepo;

    @Autowired
    private RequestValidator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String createColonist(CreateColonistRequest request) {

        List<String> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new CustomException(violations.get(0), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (!colonistRepo.existsByColonistId(request.getColonistId())) {
            Colonist colonist = new Colonist();
            colonist.setColonistId(request.getColonistId());
            colonist.setPassword(passwordEncoder.encode(request.getPassword()));
            colonist.setDateOfRegistration(new Date());

            List<Role> roles = new ArrayList<>();
            roles.add(Role.ROLE_USER);

            colonist.setRoles(roles);

            colonist = colonistRepo.save(colonist);

            return jwtTokenProvider.createToken(colonist.getColonistId(), colonist.getRoles());
        } else {
            throw new CustomException("Please choose another Id", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String authenticateColonist(String colonistId, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(colonistId, password));
            return jwtTokenProvider.createToken(colonistId, colonistRepo.findByColonistId(colonistId).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Incorrect login credentials", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String refresh(String colonistId) {
        return jwtTokenProvider.createToken(colonistId, colonistRepo.findByColonistId(colonistId).getRoles());
    }

}

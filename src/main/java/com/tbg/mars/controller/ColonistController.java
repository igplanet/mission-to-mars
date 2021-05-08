/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tbg.mars.controller;

import com.tbg.mars.service.ColonistService;
import com.tbg.mars.request.CreateColonistRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ColonistController {

    @Autowired
    private ColonistService colonistService;

    @PostMapping(value = "/signin")
    @ResponseBody
    public String authenticate(
            @RequestParam String colonistId,
            @RequestParam String password) {
        return colonistService.authenticateColonist(colonistId, password);
    }

    @PostMapping(value = "/signup")
    @ResponseBody
    public String createColonist(
            @RequestBody CreateColonistRequest createColonistRequest) {
        return colonistService.createColonist(createColonistRequest);
    }
}

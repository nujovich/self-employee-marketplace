package com.selfemployee.market.controller;

import com.selfemployee.market.dto.ProjectDto;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketplace/v1")
public class ProjectController {

    public ProjectDto getProjectDetails(@PathVariable String id) {
        return null;
        
    }
}

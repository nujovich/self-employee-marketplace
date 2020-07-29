package com.selfemployee.market.controller;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.service.ProjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketplace/v1")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping(value = "/project/{id}")
    public ProjectDto getProjectDetails(@PathVariable String id) {
        return projectServiceImpl.getProjectDetails(id);
        
    }

    @PostMapping(value = "/project")
    public ProjectDto createNewProject(@RequestBody ProjectDto projectDto) {
        return projectServiceImpl.createNewProject(projectDto);
    }
}

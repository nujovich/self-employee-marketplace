package com.selfemployee.market.controller;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.exception.PastDateException;
import com.selfemployee.market.helper.ValidatorHelper;
import com.selfemployee.market.service.ProjectServiceIF;

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
    private ProjectServiceIF projectServiceIF;

    @Autowired
    private ValidatorHelper validatorHelper;

    @GetMapping(value = "/project/{id}")
    public ProjectDto getProjectDetails(@PathVariable String id) {
        return projectServiceIF.getProjectDetails(id);
        
    }

    @PostMapping(value = "/project")
    public ProjectDto createNewProject(@RequestBody ProjectDto projectDto) throws PastDateException {
        if(validatorHelper.isDateBeforeToday(projectDto.getEndDateForBids())) {
            throw new PastDateException("The date selected is a past date.");
        }
        return projectServiceIF.createNewProject(projectDto);
    }
}

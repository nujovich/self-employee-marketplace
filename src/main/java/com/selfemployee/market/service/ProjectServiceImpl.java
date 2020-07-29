package com.selfemployee.market.service;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectServiceIF {

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectDto getProjectDetails (String id) {

        return new ProjectDto();
    }
    
}
package com.selfemployee.market.service;

import com.selfemployee.market.dto.ProjectDto;

@Service
public class ProjectServiceImpl implements ProjectServiceIF {

    public ProjectDto getProjectDetails (String id) {
        return new ProjectDto();
    }
    
}
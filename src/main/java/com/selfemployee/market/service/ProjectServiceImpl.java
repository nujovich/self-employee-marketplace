package com.selfemployee.market.service;

import java.util.Objects;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.repository.BidRepository;
import com.selfemployee.market.repository.ProjectRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectServiceIF {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BidRepository bidRepository;

    public ProjectDto getProjectDetails (String id) {

        ObjectId _id = new ObjectId(id);
        ProjectDto projectDto = null;
        Project project =  projectRepository.getProjectById(_id);
        if(Objects.nonNull(project)) {
            double minBid = bidRepository.getMinBidByProjectId(_id);
            if(minBid != 0.0) {
                projectDto = new ProjectDto(id, project.getDescription(), project.getBudget(), project.getEndDateForBids(), minBid);
            }
        }
        return projectDto;
    }

    public ProjectDto createNewProject(ProjectDto projectDto) {
        Project project = new Project(projectDto.getDescription(), projectDto.getBudget(), projectDto.getEndDateForBids());
        project = projectRepository.saveProject(project);
        projectDto.setId(project.getId().toString());
        return projectDto;
    }

    
}
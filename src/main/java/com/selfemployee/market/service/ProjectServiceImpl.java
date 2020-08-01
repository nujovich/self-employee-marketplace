package com.selfemployee.market.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.helper.ValidatorHelper;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.model.Seller;
import com.selfemployee.market.repository.BidRepository;
import com.selfemployee.market.repository.ProjectRepository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectServiceIF {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ValidatorHelper validatorHelper;

    @Override
    public ProjectDto getProjectDetails (String id) {
        ObjectId _id = new ObjectId(id);
        ProjectDto projectDto = null;
        Project project =  projectRepository.getProjectById(_id);
        if(Objects.nonNull(project)) {
            Document minBid = bidRepository.getMinBidByProjectId(_id);
            if (Objects.nonNull(minBid)) {
                if(validatorHelper.isDateBeforeToday(project.getEndDateForBids())) {
                Document seller = minBid.getList("seller", Document.class).get(0);
                projectDto = new ProjectDto.ProjectDtoBuilder(project.getId().toString(), project.getDescription(), 
                project.getBudget(), project.getEndDateForBids())
                .setMinBid(minBid.getDouble("bid"))
                .setName(seller.getString("name"))
                .setLastName(seller.getString("lastName")).build();
                } else {
                    projectDto = new ProjectDto.ProjectDtoBuilder(project.getId().toString(), project.getDescription(), 
                    project.getBudget(), project.getEndDateForBids()).setMinBid(minBid.getDouble("bid")).build();
                }
            } else {
                projectDto = new ProjectDto.ProjectDtoBuilder(project.getId().toString(), project.getDescription(), 
                project.getBudget(), project.getEndDateForBids()).build();
            }
        }
        return projectDto;
    }

    @Override
    public ProjectDto createNewProject(ProjectDto projectDto) {
        Project project = new Project(projectDto.getDescription(), projectDto.getBudget(), projectDto.getEndDateForBids());
        project = projectRepository.saveProject(project);
        projectDto = new ProjectDto.ProjectDtoBuilder(project.getId().toString(), project.getDescription(), 
                project.getBudget(), project.getEndDateForBids()).build();
        return projectDto;
    }

    
}
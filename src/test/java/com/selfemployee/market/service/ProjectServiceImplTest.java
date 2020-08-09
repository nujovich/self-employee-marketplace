package com.selfemployee.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.helper.ValidatorHelper;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.model.Project.ProjectBuilder;
import com.selfemployee.market.repository.BidRepository;
import com.selfemployee.market.repository.ProjectRepository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProjectServiceImplTest {

    @InjectMocks
    private ProjectServiceImpl projectServiceImplMock;

    @Mock
    private ProjectRepository projectRepositoryMock;

    @Mock
    private BidRepository bidRepositoryMock;

    @Mock
    private ValidatorHelper validatorHelperMock;

    @Mock
    private ProjectBuilder projectBuilder;

    private Date date;

    private Date pastDate;

    private ProjectDto projectDto;

    private ProjectDto projectDtoWithMinBid;

    private ProjectDto projectDtoWithMinBidAndSellerDetail;

    private Project projectWithMinBid;

    private Project projectWithMinBidAndPastDate;

    private Project projectEntity;

    private Project projectPersisted;

    private Document minBid;

    private Document seller;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
        date =  Date.from(LocalDate.of(2020, Month.AUGUST, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        pastDate =Date.from(LocalDate.of(2020, Month.AUGUST, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        projectDto = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, date)
        .build();
        projectDtoWithMinBid = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, date)
        .setMinBid(11000)
        .build();
        projectDtoWithMinBidAndSellerDetail = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, pastDate)
        .setMinBid(11000)
        .setName("Lucas")
        .setLastName("Johnson")
        .build();
        projectWithMinBid = new Project.ProjectBuilder("Awesome project", 12560, date)
        .setId(new ObjectId(projectDtoWithMinBid.getId())).build();
        projectWithMinBidAndPastDate = new Project.ProjectBuilder("Awesome project", 12560, pastDate)
        .setId(new ObjectId(projectDtoWithMinBid.getId())).build();
        projectPersisted = new Project.ProjectBuilder("Awesome project", 12560, date)
        .setId(new ObjectId(projectDto.getId())).build();
        projectEntity = new Project.ProjectBuilder("Awesome project", 12560, date).build();
        minBid = new Document();
        seller = new Document();
        seller.put("name", "Lucas");
        seller.put("lastName", "Johnson");
        minBid.put("bid", new Double(11000));
        minBid.put("seller", Arrays.asList(seller));      
    }

    @Test
    public void getProjectDetails_shouldReturnProjectWithMinBid() {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDtoWithMinBid.getId()))).thenReturn(projectWithMinBid);
        when(bidRepositoryMock.getMinBidByProjectId(new ObjectId(projectDtoWithMinBid.getId()))).thenReturn(minBid);
        when(validatorHelperMock.isDateBeforeToday(projectDtoWithMinBid.getEndDateForBids())).thenReturn(false);
        ProjectDto output = projectServiceImplMock.getProjectDetails(projectDtoWithMinBid.getId());
        assertEquals(projectDtoWithMinBid.getMinBid(), output.getMinBid());
        assertNotNull(output.getMinBid());
        assertEquals("N/A", output.getName());
        assertEquals("N/A", output.getLastName());
    }

    @Test
    public void getProjectDetails_shouldReturnProjectWithMinBidAndSellerDetail() {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDtoWithMinBidAndSellerDetail.getId()))).thenReturn(projectWithMinBidAndPastDate);
        when(bidRepositoryMock.getMinBidByProjectId(new ObjectId(projectDtoWithMinBidAndSellerDetail.getId()))).thenReturn(minBid);
        when(validatorHelperMock.isDateBeforeToday(projectDtoWithMinBidAndSellerDetail.getEndDateForBids())).thenReturn(true);
        seller = minBid.getList("seller", Document.class).get(0);
        ProjectDto output = projectServiceImplMock.getProjectDetails(projectDtoWithMinBidAndSellerDetail.getId());
        assertEquals(projectDtoWithMinBidAndSellerDetail.getMinBid(), output.getMinBid());
        assertNotNull(output.getMinBid());
        assertEquals(seller.get("name"), output.getName());
        assertEquals(seller.get("lastName"), output.getLastName());
    }

    @Test
    public void getProjectDetails_shouldReturnProjectDto() {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDto.getId()))).thenReturn(projectPersisted);
        when(bidRepositoryMock.getMinBidByProjectId(new ObjectId(projectDto.getId()))).thenReturn(null);
        ProjectDto output = projectServiceImplMock.getProjectDetails(projectDto.getId());
        assertEquals(projectDto.getId(), output.getId());
        assertEquals(0.0, output.getMinBid());
        assertEquals("N/A", output.getName());
        assertEquals("N/A", output.getLastName());
    }

    @Test
    public void getProjectDetails_shouldReturnNull() {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDto.getId()))).thenReturn(null);
        ProjectDto output = projectServiceImplMock.getProjectDetails(projectDto.getId());
        assertNull(output);
    }

    @Test
    public void createNewProject() {
        when(projectBuilder.build()).thenReturn(projectEntity);
        when(projectRepositoryMock.saveProject(any(Project.class))).thenReturn(projectPersisted);
        ProjectDto output = projectServiceImplMock.createNewProject(projectDto);
        assertNotNull(projectPersisted.getId());
        assertNotNull(output);
        assertEquals(projectDto.getId(), output.getId()); 
    }
    
}
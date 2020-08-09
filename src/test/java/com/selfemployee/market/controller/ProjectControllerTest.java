package com.selfemployee.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.exception.PastDateException;
import com.selfemployee.market.helper.ValidatorHelper;
import com.selfemployee.market.service.ProjectServiceIF;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectControllerMock;

    @Mock
    private ProjectServiceIF projectServiceIFMock;

    @Mock
    private ValidatorHelper validatorHelperMock;

    private ProjectDto projectDto;

    private LocalDate pastDate = LocalDate.of(2020, Month.JULY, 30);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        projectDto = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, new Date())
        .build();
    }

    @Test
    public void getProjectDetails() {
        when(projectServiceIFMock.getProjectDetails(projectDto.getId())).thenReturn(projectDto);
        ProjectDto output = projectControllerMock.getProjectDetails(projectDto.getId());
        assertSame(projectDto, output);
        assertEquals(projectDto.getId(), output.getId());
    }

    @Test
    public void createNewProject_shouldThrowPastDateException() {
        ProjectDto wrongInput = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, validatorHelperMock.asDate(pastDate))
        .build();
        when(validatorHelperMock.isDateBeforeToday(wrongInput.getEndDateForBids())).thenReturn(true);
        PastDateException ex = assertThrows(PastDateException.class, () -> {
            projectControllerMock.createNewProject(wrongInput);
         });
        assertEquals("The date selected is a past date.", ex.getMessage());
    }

    @Test
    public void createNewProject_shouldCreateNewProject() throws PastDateException {
        when(validatorHelperMock.isDateBeforeToday(projectDto.getEndDateForBids())).thenReturn(false);
        when(projectServiceIFMock.createNewProject(projectDto)).thenReturn(projectDto);
        ProjectDto output = projectControllerMock.createNewProject(projectDto);
        assertSame(projectDto, output);
        assertEquals(projectDto.getId(), output.getId());
    }
    
}
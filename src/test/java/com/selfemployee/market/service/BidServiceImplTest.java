package com.selfemployee.market.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.dto.ProjectDto;
import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.exception.NotMatchingProjectFound;
import com.selfemployee.market.model.Bid;
import com.selfemployee.market.model.Project;
import com.selfemployee.market.repository.BidRepository;
import com.selfemployee.market.repository.ProjectRepository;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class BidServiceImplTest {

    @InjectMocks
    private BidServiceImpl bidServiceImplMock;

    @Mock
    private BidRepository bidRepositoryMock;

    @Mock
    private ProjectRepository projectRepositoryMock;

    private Project projectPersisted;

    private ProjectDto projectDto;

    private BidDto bidDtoExceedBudget;

    private BidDto bidDto;

    private Bid bidPersisted;

    private Date date;

    private Document projectDoc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        date =  Date.from(LocalDate.of(2020, Month.AUGUST, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        projectDto = new ProjectDto.ProjectDtoBuilder("5f293cdf59e3326e306f6dd5", "Awesome project", 12560, date)
        .build();
        projectPersisted = new Project.ProjectBuilder("Awesome project", 12560, date)
        .setId(new ObjectId(projectDto.getId())).build();
        bidDtoExceedBudget = new BidDto("5f21ace64072cb3e72b56420", "5f293cdf59e3326e306f6dd5", "5f20cf31c0f8a6440ae395ff", 310000);
        bidDto = new BidDto("5f21ace64072cb3e72b56420", "5f293cdf59e3326e306f6dd5", "5f20cf31c0f8a6440ae395ff", 11000);
        bidPersisted = new Bid(new ObjectId("5f293cdf59e3326e306f6dd5"), new ObjectId("5f20cf31c0f8a6440ae395ff"), 11000);
        bidPersisted.setId(new ObjectId("5f21ace64072cb3e72b56420"));
        projectDoc = new Document();
        projectDoc.put("_id", new ObjectId("5f293cdf59e3326e306f6dd5"));
    }

    @Test
    public void createNewBid_shouldThrowBidExceedBudgetException() {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDto.getId()))).thenReturn(projectPersisted);
        BidExceedBudgetException ex = assertThrows(BidExceedBudgetException.class, () -> {
            bidServiceImplMock.createNewBid(bidDtoExceedBudget);
         });
        assertTrue(projectPersisted.getBudget() < bidDtoExceedBudget.getBid());
        assertEquals("Your bid exceeds the project's budget", ex.getMessage());
    }

    @Test
    public void createNewBid_shouldReturnBidDto() throws BidExceedBudgetException {
        when(projectRepositoryMock.getProjectById(new ObjectId(projectDto.getId()))).thenReturn(projectPersisted);
        when(bidRepositoryMock.saveBid(any(Bid.class))).thenReturn(bidPersisted);
        BidDto output = bidServiceImplMock.createNewBid(bidDto);
        assertNotNull(bidPersisted.getId());
        assertTrue(projectPersisted.getBudget() > bidDto.getBid());
        assertEquals(bidDto.getId(), output.getId());
    }

    @Test
    public void createAutoBid_shouldReturnNotMatchingProjectFound() {
        when(projectRepositoryMock.getProjectForAutoBid(bidDto.getBid())).thenReturn(null);
        NotMatchingProjectFound ex = assertThrows(NotMatchingProjectFound.class, () -> {
            bidServiceImplMock.createAutoBid(bidDto);
         });
        assertEquals("No matching project found for your bid. Please check budgets and retry submit a new bid", ex.getMessage());
    }

    @Test
    public void createAutoBid_shouldReturnBidDto() throws NotMatchingProjectFound {
        when(projectRepositoryMock.getProjectForAutoBid(bidDto.getBid())).thenReturn(projectDoc);
        when(bidRepositoryMock.saveBid(any(Bid.class))).thenReturn(bidPersisted);
        BidDto output = bidServiceImplMock.createAutoBid(bidDto);
        assertNotNull(bidPersisted.getId());
        assertEquals(projectDoc.get("_id").toString(), output.getProjectId());
    }


    
}
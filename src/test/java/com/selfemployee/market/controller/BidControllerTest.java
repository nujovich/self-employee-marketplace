package com.selfemployee.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import com.selfemployee.market.dto.BidDto;
import com.selfemployee.market.exception.BidExceedBudgetException;
import com.selfemployee.market.exception.NotMatchingProjectFound;
import com.selfemployee.market.service.BidServiceIF;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BidControllerTest {

    @InjectMocks
    private BidController bidControllerMock;

    @Mock
    private BidServiceIF bidServiceIFMock;

    private BidDto bidDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bidDto = new BidDto("5f21ace64072cb3e72b56420", "5f20ce37c0f8a6440ae395fe", "5f20cf31c0f8a6440ae395ff", 1200);
    }

    @Test
    public void createBid() throws BidExceedBudgetException {
        when(bidServiceIFMock.createNewBid(bidDto)).thenReturn(bidDto);
        BidDto output = bidControllerMock.createNewBid(bidDto);
        assertSame(bidDto, output);
        assertEquals(bidDto.getId(), output.getId());
    }
    
    @Test
    public void createAutoBid() throws NotMatchingProjectFound {
        when(bidServiceIFMock.createAutoBid(bidDto)).thenReturn(bidDto);
        BidDto output = bidControllerMock.createAutoBid(bidDto);
        assertSame(bidDto, output);
        assertEquals(bidDto.getId(), output.getId());
    }
}
package com.selfemployee.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import com.selfemployee.market.helper.ValidatorHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ValidatorHelperTest {

    @InjectMocks
    private ValidatorHelper validatorHelperMock;

    private Date futureDate;

    private Date pastDate;

    private LocalDate futureLocalDate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        futureDate =  Date.from(LocalDate.of(2021, Month.AUGUST, 30).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        pastDate =Date.from(LocalDate.of(2020, Month.AUGUST, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        futureLocalDate = LocalDate.of(2021, Month.AUGUST, 30);
    }
    
    @Test
    public void isDateBeforeToday_shouldReturnTrue() {
        assertTrue(validatorHelperMock.isDateBeforeToday(pastDate));
    }

    @Test
    public void isDateBeforeToday_shouldReturnFalse() {
        assertFalse(validatorHelperMock.isDateBeforeToday(futureDate));
    }

    @Test
    public void asDate() {
        assertEquals(futureDate, validatorHelperMock.asDate(futureLocalDate));
    }
}
package com.selfemployee.market.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ValidatorHelper {
    
    public boolean isDateBeforeToday(Date date) {
        LocalDate today = LocalDate.now();
        LocalDate endDateForBids = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return (endDateForBids.isBefore(today));
        
    }

    public Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
      }
}
package com.wxweven.datetime;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeTest.class);

    @Test
    public void testDaysBetween() {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime d1 = DateTime.parse("2017-03-22 23:59:59", format);
        DateTime d2 = DateTime.parse("2017-03-22 00:00:00", format);

        Assert.assertEquals(DateUtils.isSameDay(d1.toDate(), d2.toDate()), true);

        int days = Days.daysBetween(d1, d2).getDays();
        LOGGER.info("days: {}", days);
    }

}

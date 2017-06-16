package com.wxweven.enums;

import org.junit.Test;

public class EnumTest {

    @Test
    public void test() {
        WeekDayEnum wd = WeekDayEnum.TUS;
        System.out.println(wd);

        for (WeekDayEnum wds : WeekDayEnum.values()) {
            System.out.println(wds);
        }
    }



}

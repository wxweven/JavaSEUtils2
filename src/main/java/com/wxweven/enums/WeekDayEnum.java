package com.wxweven.enums;

public enum WeekDayEnum {
    SUN(0, "星期天"),
    MON(1, "星期一"),
    TUS(2, "星期二"),
    WED(3, "星期三"),
    THU(4, "星期四"),
    FRI(5, "星期五"),
    SAT(6, "星期六");

    private int code;
    private String msg;

    WeekDayEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static WeekDayEnum getByCode(int code) {
        for (WeekDayEnum item : WeekDayEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }

        return SUN;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "WeekDayEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
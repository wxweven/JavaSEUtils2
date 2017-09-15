package com.wxweven.http;

import java.util.List;

/**
 * @author wxweven
 * @date 2017/8/18
 */
public class TPVOS {
    private List<TPVO> data;

    public List<TPVO> getData() {
        return data;
    }

    public void setData(List<TPVO> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TPVOS{" +
                "data=" + data +
                '}';
    }
}

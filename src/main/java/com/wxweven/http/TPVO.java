package com.wxweven.http;

/**
 * @author wxweven
 * @date 2017/8/18
 */
public class TPVO {
    private String appkey;
    private String spanname;
    private Integer costMean;
    private Integer count;
    private Integer countClient;
    private Integer failCount;
    private Integer failCountClient;
    private Integer upper;
    private Integer upper50;
    private Integer upper90;
    private Integer upper95;
    private Integer upper99;
    private Integer upper999;


    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getSpanname() {
        return spanname;
    }

    public void setSpanname(String spanname) {
        this.spanname = spanname;
    }

    public Integer getCostMean() {
        return costMean;
    }

    public void setCostMean(Integer costMean) {
        this.costMean = costMean;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountClient() {
        return countClient;
    }

    public void setCountClient(Integer countClient) {
        this.countClient = countClient;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getFailCountClient() {
        return failCountClient;
    }

    public void setFailCountClient(Integer failCountClient) {
        this.failCountClient = failCountClient;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public Integer getUpper50() {
        return upper50;
    }

    public void setUpper50(Integer upper50) {
        this.upper50 = upper50;
    }

    public Integer getUpper90() {
        return upper90;
    }

    public void setUpper90(Integer upper90) {
        this.upper90 = upper90;
    }

    public Integer getUpper95() {
        return upper95;
    }

    public void setUpper95(Integer upper95) {
        this.upper95 = upper95;
    }

    public Integer getUpper99() {
        return upper99;
    }

    public void setUpper99(Integer upper99) {
        this.upper99 = upper99;
    }

    public Integer getUpper999() {
        return upper999;
    }

    public void setUpper999(Integer upper999) {
        this.upper999 = upper999;
    }

    @Override
    public String toString() {
        return "TPVO{" +
                "appkey='" + appkey + '\'' +
                ", spanname='" + spanname + '\'' +
                ", costMean=" + costMean +
                ", count=" + count +
                ", countClient=" + countClient +
                ", failCount=" + failCount +
                ", failCountClient=" + failCountClient +
                ", upper=" + upper +
                ", upper50=" + upper50 +
                ", upper90=" + upper90 +
                ", upper95=" + upper95 +
                ", upper99=" + upper99 +
                ", upper999=" + upper999 +
                '}';
    }
}

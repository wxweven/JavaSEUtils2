package com.wxweven.beanutils;

public class BeanVO {
    private int     id;
    private Integer name;
    private long    pid;
    private String  info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "BeanVO{" +
                "id=" + id +
                ", name=" + name +
                ", pid=" + pid +
                ", info='" + info + '\'' +
                '}';
    }
}

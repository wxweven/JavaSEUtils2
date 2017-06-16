package com.wxweven.beanutils;

public class BeanBO {
    private Integer id;
    private String  name;
    private Long    pid;
    private Double  number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanBO beanBO = (BeanBO) o;

        if (id != null ? !id.equals(beanBO.id) : beanBO.id != null) return false;
        if (name != null ? !name.equals(beanBO.name) : beanBO.name != null) return false;
        if (pid != null ? !pid.equals(beanBO.pid) : beanBO.pid != null) return false;
        return number != null ? number.equals(beanBO.number) : beanBO.number == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BeanBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", number=" + number +
                '}';
    }
}

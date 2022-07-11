package com.wjh.pojo;

import java.io.Serializable;

public class Dept implements Serializable {
    private long deptno;
    private String dname;

    //这个数据存储在哪个数据库字段，一个服务对应一个数据库
    private String db_source;

    public long getDeptno() {
        return deptno;
    }

    public void setDeptno(long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDb_source() {
        return db_source;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }


}

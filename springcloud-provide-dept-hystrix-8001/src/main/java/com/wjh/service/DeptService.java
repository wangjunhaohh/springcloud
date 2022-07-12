package com.wjh.service;

import com.wjh.dao.DeptDao;
import com.wjh.pojo.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService implements DeptDao {

    @Resource
    private DeptDao dao;

    public boolean addDept(Dept dept) {
        return dao.addDept(dept);
    }

    public Dept queryById(Long id) {
        return dao.queryById(id);
    }

    public List<Dept> queryAll() {
        return dao.queryAll();
    }
}

package com.tfjybj.integral.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "olddeptlist")
public class NoUserOrgModel {

    private List<Integer> dept;

    @Override
    public String toString() {
        return "NoUserOrg{" +
                "dept=" + dept +
                '}';
    }

    public List<Integer> getDept() {
        return dept;
    }

    public void setDept(List<Integer> dept) {
        this.dept = dept;
    }
}

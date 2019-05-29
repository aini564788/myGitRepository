package com.lmh.vo;

import com.lmh.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserRoleVo extends User {

    private List<Long> rids=new ArrayList<>();
    private List<String> rnames=new ArrayList<>();

    @Override
    public String toString() {
        return "UserRoleVo{" +
                super.toString()+
                "rids=" + rids +
                ", rnames=" + rnames +
                '}';
    }

    public UserRoleVo() {
    }

    public List<Long> getRids() {
        return rids;
    }

    public void setRids(List<Long> rids) {
        this.rids = rids;
    }

    public List<String> getRnames() {
        return rnames;
    }

    public void setRnames(List<String> rnames) {
        this.rnames = rnames;
    }

    public UserRoleVo(List<Long> rids, List<String> rnames) {
        this.rids = rids;
        this.rnames = rnames;
    }
}

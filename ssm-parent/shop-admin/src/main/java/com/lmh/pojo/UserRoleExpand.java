package com.lmh.pojo;

import java.util.List;

public class UserRoleExpand extends User{

    private List<Role> list;

    public UserRoleExpand() {
    }

    @Override
    public String toString() {
        return "UserRoleExpand{" +
                super.toString()+
                "list=" + list +
                '}';
    }

    public List<Role> getList() {
        return list;
    }

    public void setList(List<Role> list) {
        this.list = list;
    }

    public UserRoleExpand(List<Role> list) {
        this.list = list;
    }
}

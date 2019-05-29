package com.lmh.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Role {
    private Long roleUkid;

    private String roleName;

    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd, HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd, HH:mm:ss")
    private Date createTime;

    private Long createUserId;

    private Date modifyTime;

    private Long modifyUserId;

    public Long getRoleUkid() {
        return roleUkid;
    }

    public void setRoleUkid(Long roleUkid) {
        this.roleUkid = roleUkid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }
}
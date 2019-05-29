package com.lmh.service.impl;


import com.lmh.mapper.PermissionMapper;
import com.lmh.mapper.RolePermissionMapper;
import com.lmh.pojo.Permission;
import com.lmh.pojo.PermissionExample;
import com.lmh.pojo.RolePermissionExample;
import com.lmh.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Permission> queryPermissionsByPage(Integer page, Integer limit, Permission permission) {

        return permissionMapper.queryPermissionsByPage((page-1)*limit,limit,permission);
    }

    @Override
    public Long queryCountPermissions( Permission permission) {
        return permissionMapper.queryCountPermissions(permission);
    }


    @Override
    public boolean insertPermission(Permission permission) {
        return permissionMapper.insertSelective(permission)>0;
    }

    @Override
    public List<Permission> queryAllFirst() {
        PermissionExample pe=new PermissionExample();
        pe.createCriteria().andParentidEqualTo(0L);
        return permissionMapper.selectByExample(pe);
    }

    @Override
    public boolean updateStatus(boolean status, Long peId) {
        Permission permission=new Permission();
        permission.setAvailable(status?"1":"0");
        permission.setPerId(peId);
        int i = permissionMapper.updateByPrimaryKeySelective(permission);
        return i>0;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        int i = permissionMapper.deleteBatch(ids);
        permissionMapper.deleteBatchRolePermission(ids);
        return i>0;
    }

    @Override
    public boolean deleteById(Long id){
        RolePermissionExample rolePermissionExample=new RolePermissionExample();
        rolePermissionExample.createCriteria().andSysPermissionIdEqualTo(id);
        rolePermissionMapper.deleteByExample(rolePermissionExample);
        return permissionMapper.deleteByPrimaryKey(id)>0;
    }

}

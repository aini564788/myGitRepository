package com.lmh.service.impl;

import com.lmh.mapper.RoleMapper;
import com.lmh.mapper.RolePermissionMapper;
import com.lmh.pojo.Role;
import com.lmh.pojo.RolePermission;
import com.lmh.pojo.RolePermissionExample;
import com.lmh.pojo.ZTree;
import com.lmh.service.RoleService;
import com.lmh.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public ResultVo queryRoleByPages(Integer page, Integer limit, Integer status, String no) {

        List<Role> list = roleMapper.selectRoleByPages((page-1)*limit,limit,no,status);
        Long count = roleMapper.selectRoleByPagesCount(no,status);

        return ResultVo.success(count,list);
    }

    @Override
    public boolean addRole(String roleName) {
        Role role =new Role();
        role.setCreateTime(new Date());
        role.setRoleName(roleName);
        role.setStatus(1);
        return roleMapper.insertSelective(role)>0;
    }

    @Override
    public Role queryRoleByRid(Long roleUkid) {
        return roleMapper.selectByPrimaryKey(roleUkid);
    }

    @Override
    public boolean modifyRole(Role role) {
        role.setModifyTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(role)>0;
    }

    @Override
    public boolean deleteRoleById(Long id) {
        return roleMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean deleteRoleByBatch(Long[] ids) {

        int i = roleMapper.deleteByBatch(ids);
        return i>0;
    }

    @Override
    public List<Role> queryAll() {
        List<Role> roles = roleMapper.selectByExample(null);
        return roles;
    }

    @Override
    public List<ZTree> queryAllPermissions(Long roleUkid) {

        return rolePermissionMapper.queryAllPermissionsByRid(roleUkid);
    }

    @Override
    @Transactional
    public boolean updateRolePermissions(Long roleUkid, Long[] aids) {
//        先删除原有权限
        RolePermissionExample rolePermissionExample=new RolePermissionExample();
        rolePermissionExample.createCriteria().andSysRoleIdEqualTo(roleUkid);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        int i=1;
        if(rolePermissions.size()>0) {
             i = rolePermissionMapper.deleteByExample(rolePermissionExample);
        }
        if(aids.length>0){
           rolePermissionMapper.insertBatch(roleUkid, aids);
        }
//        在添加新的权限
        return i>0;
    }
}

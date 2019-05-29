package com.lmh.service;

import com.lmh.pojo.Role;
import com.lmh.pojo.ZTree;
import com.lmh.vo.ResultVo;

import java.util.List;

public interface RoleService {
    ResultVo queryRoleByPages(Integer page, Integer limit, Integer status, String no);

    boolean addRole(String roleName);

    Role queryRoleByRid(Long roleUkid);

    boolean modifyRole(Role role);

    boolean deleteRoleById(Long id);

    boolean deleteRoleByBatch(Long[] ids);

    List<Role> queryAll();

    List<ZTree> queryAllPermissions(Long roleUkid);

    boolean updateRolePermissions(Long roleUkid, Long[] aids);
}

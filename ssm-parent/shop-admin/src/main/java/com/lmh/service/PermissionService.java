package com.lmh.service;

import com.lmh.pojo.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> queryPermissionsByPage(Integer page, Integer limit, Permission permission);

    Long queryCountPermissions( Permission permission);

    List<Permission> queryAllFirst();

    boolean insertPermission(Permission permission);

    boolean updateStatus(boolean status, Long peId);

    boolean deleteBatch(Long[] ids);

    boolean deleteById (Long id);
}

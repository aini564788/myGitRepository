package com.lmh.mapper;

import com.lmh.pojo.Permission;
import com.lmh.pojo.PermissionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Long perId);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long perId);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> queryPermissionsByPage(@Param("page") int i, @Param("limit") Integer limit, @Param("pe") Permission permission);

    Long queryCountPermissions(@Param("pe") Permission permission);

    int deleteBatch(@Param("ids") Long[] ids);

    int deleteBatchRolePermission(@Param("ids") Long[] ids);
}
package com.lmh.mapper;

import com.lmh.pojo.RolePermission;
import com.lmh.pojo.RolePermissionExample;
import com.lmh.pojo.ZTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    long countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    List<RolePermission> selectByExample(RolePermissionExample example);

    RolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermission record, @Param("example") RolePermissionExample example);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<ZTree> queryAllPermissionsByRid(@Param("rid") Long roleUkid);

    int insertBatch(@Param("rid") Long roleUkid,@Param("aids") Long[] aids);
}
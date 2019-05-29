package com.lmh.mapper;

import com.lmh.pojo.Role;
import com.lmh.pojo.RoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Long roleUkid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleUkid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByPages(@Param("page") int currentPage, @Param("limit") Integer limit, @Param("no") String no,@Param("status") Integer status);

    Long selectRoleByPagesCount(@Param("no") String no,@Param("status") Integer status);

    int deleteByBatch(@Param("ids") Long[] ids);
}
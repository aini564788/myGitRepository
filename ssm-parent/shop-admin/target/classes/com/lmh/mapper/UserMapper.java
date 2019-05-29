package com.lmh.mapper;

import com.lmh.pojo.User;
import com.lmh.pojo.UserExample;
import com.lmh.pojo.UserRoleExpand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPassword(@Param("userAccount") String userAccount, @Param("password") String password);

    int updateHeadImage(@Param("id") Long userId,@Param("headImage") String file);

    int updatePassword(@Param("password") String password, @Param("id") Long userId);

    List<UserRoleExpand> queryDataByPages(@Param("page") int currentPage, @Param("limit") Integer limit, @Param("no") String no,@Param("mobileNumber") String mobileNumber, @Param("status") Integer status);

    long selectCount(@Param("no") String no, @Param("mobileNumber") String mobileNumber, @Param("status")Integer status);

    int deleteBatch(@Param("ids") Long[] ids);

    int updateStatus(@Param("userId") Long userId,@Param("status") int status);

    int deleteRole(@Param("userId") Long userId);

    int insertRole(@Param("uid") Long userId,@Param("rids") Long[] rids);

    List<String> queryRoleByUserName(@Param("userName") String userName);

    List<String> queryPermissionsByUserName(@Param("userName") String userName);
}
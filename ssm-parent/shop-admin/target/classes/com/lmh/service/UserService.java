package com.lmh.service;

import com.lmh.pojo.User;
import com.lmh.vo.ResultVo;

import java.util.List;

public interface UserService {


    User queryUserExits(String userAccount, String encrypt);

    boolean updateHeadImage(Long userId, String s);

    String queryPasswordByExample(Long uid);

    boolean updatePassword(User user);

    ResultVo queryDataByPages(Integer page, Integer limit, String no, String mobileNumber, Integer status);

    User queryUserById(Long userId);

    boolean updateUser(User user);

    boolean addUser(User user,Long id);

    boolean queryUserAccountExist(String userAccount);

    boolean deleteByUid(Long userId);

    boolean deleteBatch(Long[] ids);

    boolean updateStatus(Long userId, int status);

    boolean modifyUserRoles(Long userId, Long[] rids);

    User queryUserByName(String userName);

    List<String> queryRoleByUserName(String userName);

    List<String> queryPermissionsByUserName(String userName);
}

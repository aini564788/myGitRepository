package com.lmh.service.impl;

import com.lmh.mapper.UserMapper;
import com.lmh.pojo.Role;
import com.lmh.pojo.User;
import com.lmh.pojo.UserExample;
import com.lmh.pojo.UserRoleExpand;
import com.lmh.service.UserService;
import com.lmh.ssm.MD5Utils;
import com.lmh.vo.ResultVo;
import com.lmh.vo.UserRoleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user,Long id) {
        user.setCreateTime(new Date());
        user.setCreateUserId(id);
        user.setStatus(1);
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        Random random=new Random();
        int jpg = random.nextInt(5);
        if("男".equals(user.getSex())){
            user.setHeadimageurl("static/imgs/rehead/"+jpg+".jpg");
        }else {
            user.setHeadimageurl("static/imgs/rehead/"+(jpg+5)+".jpg");
        }
        int i = userMapper.insertSelective(user);
        return i>0;
    }

    @Override
    public boolean updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i>0;
    }

    @Override
    public User queryUserById(Long userId) {
        User query = userMapper.selectByPrimaryKey(userId);
        User user =new User();
        user.setUserId(query.getUserId());
        user.setSex(query.getSex());
        user.setUserName(query.getUserName());
        user.setUserAccount(query.getUserAccount());
        user.setEmail(query.getEmail());
        user.setMobileNumber(query.getMobileNumber());
        return user;
    }

    @Override
    public ResultVo queryDataByPages(Integer page, Integer limit, String no, String mobileNumber, Integer status) {
        List<UserRoleVo> data =new ArrayList<>();
        List<UserRoleExpand> list=userMapper.queryDataByPages((page-1)*limit,limit,no,mobileNumber,status);
        for(UserRoleExpand ur:list){
            List<Role> roles = ur.getList();
            UserRoleVo userRoleVo=new UserRoleVo();
            BeanUtils.copyProperties(ur,userRoleVo);
            for(Role role:roles){
                String roleName = role.getRoleName();
                Long rid = role.getRoleUkid();
                userRoleVo.getRnames().add(roleName);
                userRoleVo.getRids().add(rid);
            }
            data.add(userRoleVo);
        }
        long count=userMapper.selectCount(no,mobileNumber,status);
        return ResultVo.success(count,data);

    }


    @Override
    public User queryUserExits(String userAccount, String password) {
        return userMapper.selectByPassword(userAccount,password);
    }

    @Override
    public String queryPasswordByExample(Long uid) {
        return userMapper.selectByPrimaryKey(uid).getPassword();
    }

    @Override
    public boolean updateHeadImage(Long userId,String file) {
       int i = userMapper.updateHeadImage(userId,file);
        return i>0 ;
    }

    @Override
    public boolean updatePassword(User user) {
        int i = userMapper.updatePassword(user.getPassword(),user.getUserId());
        return i>0;
    }

    @Override
    public boolean queryUserAccountExist(String userAccount) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserAccountEqualTo(userAccount);
        List<User> users = userMapper.selectByExample(userExample);
        System.out.println(users);
        return users.size()>0;
    }

    @Override
    public boolean deleteByUid(Long userId) {
        System.out.println(userId);
        return userMapper.deleteByPrimaryKey(userId)>0;
    }

    @Override
    public boolean deleteBatch(Long[] ids) {
        int i = userMapper.deleteBatch(ids);
        return i>0;
    }

    @Override
    public boolean updateStatus(Long userId, int status) {
        int i = userMapper.updateStatus(userId,status);
        return i>0;
    }

    @Override
    @Transactional
    public boolean modifyUserRoles(Long userId, Long[] rids) {
        if(rids!=null) {
            int i = userMapper.deleteRole(userId);
            int j = userMapper.insertRole(userId, rids);
        }else {
            int i = userMapper.deleteRole(userId);
        }

        return true;
    }

    @Override
    public User queryUserByName(String userName) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andUserAccountEqualTo(userName);
        List<User> users = userMapper.selectByExample(userExample);
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<String> queryRoleByUserName(String userName) {

        return userMapper.queryRoleByUserName(userName);
    }

    @Override
    public List<String> queryPermissionsByUserName(String userName) {
        return userMapper.queryPermissionsByUserName(userName);
    }
}

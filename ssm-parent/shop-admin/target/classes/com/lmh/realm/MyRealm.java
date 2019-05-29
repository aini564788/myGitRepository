package com.lmh.realm;

import com.lmh.pojo.User;
import com.lmh.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String userName = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询当前用户拥有的权限和角色
        List<String> roleList= userService.queryRoleByUserName(userName);
        List<String> permissions= userService.queryPermissionsByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        User user = userService.queryUserByName(userName);
        if(user==null){
            throw new UnknownAccountException("没有该用户");
        }
        ByteSource byteSource = ByteSource.Util.bytes("1231~!@##%$RT!@#@/.%#$@");

        return new SimpleAuthenticationInfo(user.getUserAccount(),user.getPassword(),byteSource,this.getName());
    }
}

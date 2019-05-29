package com.lmh.controller;

import com.lmh.pojo.Role;
import com.lmh.service.RoleService;
import com.lmh.vo.ResultVo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("role/list/query")
    public Object queryAllRolesByPages(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit,
                                       Integer status,
                                       String no){
        ResultVo vo =  roleService.queryRoleByPages(page,limit,status,no);
        return vo;
    }

    @ResponseBody
    @RequestMapping("role/add/do")
    public Object addRole( String roleName){
        if("".equals(roleName)|| roleName==null){
            return ResultVo.error();
        }
        boolean b = roleService.addRole(roleName);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }
    @ResponseBody
    @RequestMapping("role/modify/query")
    public Object modifyRole(@RequestParam Long roleUkid){
       Role role = roleService.queryRoleByRid(roleUkid);
       return role;
    }

    @ResponseBody
    @RequestMapping("role/modify/do")
    public Object modifyRoleDo(Role role){
        boolean b = roleService.modifyRole(role);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }
    @ResponseBody
    @RequestMapping(value = "/role/delete/single/{id}",method = RequestMethod.DELETE)
    public Object deleteRoleById(@PathVariable Long id){
        boolean b = roleService.deleteRoleById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }
    @ResponseBody
    @RequestMapping(value = "/role/delete/batch",method = RequestMethod.DELETE)
    public Object deleteBatch(@RequestParam Long[] ids){
        if(ids.length==0){
            return ResultVo.error();
        }
        boolean b = roleService.deleteRoleByBatch(ids);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    @ResponseBody
    @RequestMapping("role/allroles/query")
    public Object queryAllRoles(){
        List<Role> list = roleService.queryAll();
        return list;
    }

    @ResponseBody
    @RequestMapping("role/ztree/query")
    public Object queryAllPermissions(@NonNull Long roleUkid){

        return roleService.queryAllPermissions(roleUkid);
    }
    @ResponseBody
    @RequestMapping("role/ztree/update")
    public Object updateRolePermissions(@NonNull Long rid,Long [] aids){
        boolean b = roleService.updateRolePermissions(rid,aids);

        return b?ResultVo.success():ResultVo.error();
    }

}

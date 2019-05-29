package com.lmh.controller;

import com.lmh.pojo.Permission;
import com.lmh.service.PermissionService;
import com.lmh.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @ResponseBody
    @RequestMapping(value = "permissions", method = RequestMethod.GET)
    public Object queryPermissionsByPage(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer limit,
                                         Permission permission) {
        List<Permission> list = permissionService.queryPermissionsByPage(page, limit, permission);
        Long count = permissionService.queryCountPermissions(permission);
        if (list != null && list.size() > 0) {
            return ResultVo.success(count, list);
        }

        return ResultVo.error();

    }

    @ResponseBody
    @RequestMapping("permission/menus/query")
    public Object queryFirstPermissions() {
        return permissionService.queryAllFirst();
    }

    @RequestMapping("permission/add/do")
    public Object addPermission(Permission permission) {
        if("menu".equals(permission.getType())){
            System.out.println(11111);
            permission.setParentid(0L);
        }
        boolean b = permissionService.insertPermission(permission);
        if (b) {
            return "permission/permissionlist";
        }
        return "forward:permission/add/do";
    }

    @ResponseBody
    @RequestMapping("permission/active/update")
    public Object updateStatus(boolean status, Long peId) {
        boolean b = permissionService.updateStatus(status, peId);
        if (b) {
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    //批量删除权限
    @ResponseBody
    @RequestMapping(value = "permission/delete/batch",method = RequestMethod.DELETE)
    public Object deleteBatch(@RequestParam Long[] ids) {
        boolean b = permissionService.deleteBatch(ids);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }

    @ResponseBody
    @RequestMapping(value = "permission/delete/{id}",method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable Long id){
        boolean b = permissionService.deleteById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }


}

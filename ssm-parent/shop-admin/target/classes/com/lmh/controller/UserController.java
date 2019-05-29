package com.lmh.controller;

import com.google.code.kaptcha.Constants;
import com.lmh.pojo.Password;
import com.lmh.pojo.User;
import com.lmh.service.UserService;
import com.lmh.ssm.MD5Utils;
import com.lmh.ssm.UUIDUtils;
import com.lmh.vo.ResultVo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping("userlogin")
    public String login(@RequestParam String userAccount,
                        @RequestParam String password,
                        @RequestParam String imgCode,
                         HttpServletRequest request){
        String code =(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        log.info(userAccount+"============"+"开始登陆");
//        if(!code.equalsIgnoreCase(imgCode)){
//            return "forward:/WEB-INF/v/login.jsp?msg=500";
//        }
        log.info(code+" ---- "+imgCode);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userAccount,password);
        token.setRememberMe(true);
        try {
            subject.login(token);
        } catch (UnknownAccountException ua) {
            log.error("用户名不存在");
            return "redirect:/";
        }catch (IncorrectCredentialsException ice){
            log.error("密码错误");
            return "redirect:/";
        }catch (LockedAccountException lae){
            log.error("用户已被锁定");
            return "redirect:/";
        }
        return "index";
    }
    @RequestMapping("userlogin222")
    public String login222(@RequestParam String userAccount,
                        @RequestParam String password,
                        @RequestParam String imgCode,
                        HttpServletRequest request){
        String code =(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(!code.equalsIgnoreCase(imgCode)){
//            return "forward:/WEB-INF/v/login.jsp?msg=500";
//        }
        User query=userService.queryUserExits(userAccount, MD5Utils.encrypt(password));
        if(query!=null){
            request.getSession().setAttribute("activeUser",query);
            return "index";
        }else {
            return "forward:/WEB-INF/v/login.jsp?msg=500";
        }

    }
    @ResponseBody
    @RequestMapping("uploadheadimage")
    public Object uploadHeadImage(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException {

        String realPath = request.getServletContext().getRealPath("static/imgs/head");
        File dir= new File(realPath);
        if(!dir.isDirectory()){
            dir.delete();
            dir.mkdirs();
        }
        if(!file.isEmpty()){
            //1.上传到服务器
            //获取后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName= UUIDUtils.getUUID().replaceAll("-","");
            String pathname=realPath+"/"+fileName+suffix;//新头像全限定名
            File dest =new File(pathname);
            file.transferTo(dest);
            //2.保存路径到数据库
            User activeUser = (User)request.getSession().getAttribute("activeUser");
            boolean b = userService.updateHeadImage(activeUser.getUserId(),"static/imgs/head/"+fileName+suffix);
            if (b){
                activeUser.setHeadimageurl("static/imgs/head/"+fileName+suffix);
                return ResultVo.success();
            }else {
                return ResultVo.error("上传失败，服务器异常",null,null);
            }

        }else {
            return ResultVo.error();
        }
    }
    @ResponseBody
    @RequestMapping(value = "modifyPassword",method = RequestMethod.PUT)
    public Object modifyPassword(@RequestBody Password password, HttpSession session){
        if(!password.getNewPassword().equals(password.getReNewPassword())){
            return ResultVo.error(400);
        }
       User user = (User) session.getAttribute("activeUser");
        Long uid = user.getUserId();
        String realPassword = userService.queryPasswordByExample(uid);
        String md5Password = MD5Utils.encrypt(password.getPassword());
        if(realPassword.equals(md5Password)){
            user.setPassword(MD5Utils.encrypt(password.getNewPassword()));
            boolean b = userService.updatePassword(user);
            return ResultVo.success();
        }
        return ResultVo.error(405);
    }
    @RequiresPermissions("user:view")
    @ResponseBody
    @RequestMapping("user/list/query")
    public Object searchUsers(Integer page,Integer limit,String no,String mobileNumber,Integer status){

        ResultVo vo=userService.queryDataByPages(page,limit,no,mobileNumber,status);

        return vo;
    }


    @RequestMapping("user/modify/query")
    public Object queryModifyed(Long userId, Model model){

       User user = userService.queryUserById(userId);
       model.addAttribute("user",user);
       return "user/usermodify";
    }
    @RequestMapping("user/modify/do")
    public Object doModify(User user){

        boolean b = userService.updateUser(user);

        if(b){
            return "user/userlist";
        }else {
            return "forward:user/modify/query?userId="+user.getUserId().toString();
        }
    }

    @ResponseBody
    @RequestMapping("user/account/query")
    public Object checkAccountExist(String userAccount){
        boolean b = userService.queryUserAccountExist(userAccount);
        if(b) {
            return ResultVo.error();
        }else {
            return ResultVo.success();
        }
    }

    @RequestMapping("user/update/add")
    public Object doAdd(User user,HttpSession session){
       boolean b = userService.addUser(user,((User)session.getAttribute("activeUser")).getUserId());
       if(b){
            return "user/userinfo";
       }else{
           return "user/useradd";
       }
    }
    @ResponseBody
    @RequestMapping(value = "user/delete/{userId}",method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable Long userId){
        boolean b = userService.deleteByUid(userId);
        System.out.println(b);
        if(b){
            return ResultVo.success();
        }else {
            return ResultVo.error();
        }
    }
    @ResponseBody
    @RequestMapping(value = "user/delete/batch",method = RequestMethod.DELETE)
    public Object deleteBatch(@RequestParam("ids") Long[] ids){
        boolean b = userService.deleteBatch(ids);
        if(b){
            return ResultVo.success();
        }else {
            return ResultVo.error();
        }
    }

    @ResponseBody
    @RequestMapping("user/status/update")
    public Object changeStatus(@NonNull Long userId, boolean status){
        boolean b = userService.updateStatus(userId,status?1:0);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.error();
    }
    @ResponseBody
    @RequestMapping("user/modifyRole/update")
    public Object modifyUserRoles(@NonNull Long userId,Long[] rids){
       boolean b = userService.modifyUserRoles(userId,rids);
       if(b){
           return ResultVo.success();
       }
       return ResultVo.error();
    }
    @ResponseBody
    @RequestMapping("queryCurrentUser")
    public Object queryCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        String userName = (String)subject.getPrincipal();
        User user = userService.queryUserByName(userName);
        subject.getSession().setAttribute("activeUser",user);
        user.setPassword("");
        return user;
    }
}

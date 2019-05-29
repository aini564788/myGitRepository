<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../includes/include.jsp"%>
</head>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required  lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input type="password" name="newPassword" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">再次输入新密码</label>
        <div class="layui-input-inline">
            <input type="password" name="reNewPassword" required lay-verify="required" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            $.ajax({
                url:"${pageContext.request.contextPath}/modifyPassword",
                type:"PUT",
                contentType:"application/json",
                dataType:"json",
                data:JSON.stringify(data.field),
                success:function (res) {
                    if(res.code==0){
                        layer.msg('success',{icon:6,time:2000},function () {
                            layer.msg('success',{icon:6});
                            sessionStorage.removeItem("activeUser");
                            window.location.href="${pageContext.request.contextPath}/";
                        });
                    }else if(res.code==405){
                        layer.msg('原密码错误',{icon:5});
                    }else{
                        layer.msg("两次密码不一致",{icon:5})
                    }
                } ,
                error: function(){
                    layer.msg('error',{icon:5});
                }
            });
            // layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>

<body>

</body>
</html>

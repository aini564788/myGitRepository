<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/22
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>

<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="${pageContext.request.contextPath}/user/modify/do" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户序号</label>
            <div class="layui-input-block">
                <input type="text" name="userId" value="${user.userId}"  readonly="readonly" autocomplete="off"
                       class="layui-input layui-bg-gray" id="f0">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户账号</label>
            <div class="layui-input-block">
                <input type="text" name="userAccount" value="${user.userAccount}"  readonly="readonly" autocomplete="off"
                       class="layui-input layui-bg-gray" id="f1">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" value="${user.userName}" id="f2" lay-verify="required" autocomplete="off"
                       placeholder="请输入名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text"  lay-verify="required|phone"  name="mobileNumber" value="${user.mobileNumber}" id="f3" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" id="f4" value="${user.email}" lay-verify="required" autocomplete="off"
                       placeholder="请输入周期" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <input type="radio" name="sex"  ${user.sex eq '男' ? "checked":""} value="男" title="男">
                <input type="radio" name="sex" value="女" ${user.sex eq '女' ? "checked":""} title="女" >
            </div>
        </div>



        <div class="layui-form-item">
            <input class="layui-btn"  style="margin-left: 10%"  type="submit" value="确认修改">
        </div>
    </form>
</div>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use([ 'form', 'laydate' ],
        function() {
            var form = layui.form,
                layer = layui.layer,
                laydate = layui.laydate;
            //重新渲染表哥
            form.render();

        });
</script>

</body>
</html>

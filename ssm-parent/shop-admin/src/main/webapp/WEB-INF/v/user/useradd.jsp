<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/22
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<div class="layui-container" style="margin-top: 5px">

    <form class="layui-form" action="${pageContext.request.contextPath}/user/update/add" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户账号</label>
            <div class="layui-input-block">
                <input type="text" name="userAccount" value="${user.userAccount}"  id="userAccount"   lay-verify="required|nounique" placeholder="请输入用户账号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户密码</label>
            <div class="layui-input-block">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
            <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" name="userName" value="${user.userName}" id="f2" lay-verify="required" autocomplete="off"
                       placeholder="真实姓名" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text"  lay-verify="required|phone" value="${user.mobileNumber}"   placeholder="手机号"  name="mobileNumber" value="${user.mobileNumber}" id="f3" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="email" id="f4" value="${user.email}" lay-verify="required|email" autocomplete="off"
                       placeholder="请输入邮箱" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-block">
                <c:if test="${!empty user.sex}">
                    <input type="radio" name="sex"  ${user.sex eq '男' ? "checked":""} value="男" title="男">
                    <input type="radio" name="sex"   value="女" ${user.sex eq '女' ? "checked":""} title="女" >
                </c:if>
                <c:if test="${empty user.sex}">
                    <input type="radio" name="sex"  checked value="男" title="男">
                    <input type="radio" name="sex"   value="女" title="女" >
                </c:if>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            </div>
        </div>
    </form>

</div>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use([ 'form', 'laydate','layer' ],
        function() {
            var form = layui.form,
                layer = layui.layer,
                laydate = layui.laydate;
            //重新渲染表哥
            form.render();


            form.verify({
                nounique: function(value, item) { //value：表单的值、item：表单的DOM对象
                   // alert(111+"------>"+value);
                    var msg;
                    $.ajax({
                        url:'${pageContext.request.contextPath}/user/account/query',
                        data:{"userAccount":value},
                        dataType:'json',
                        type:'post',
                        async:false,//要使用同步的  不要使用异步的
                        cache:false,
                        success:function (obj) {
                           if(obj.code!=0){//  code=0  不存在
                              msg="用户名已经存在";

                           }
                        },
                        error:function () {
                            layer.msg('服务器异常',{icon:5})
                        }
                    });

                   return msg;//一定要在这里返回 不能再ajax里面返回 否则不行

                }
                });

        });
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/24
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<div class="layui-container">



<table class="layui-table " lay-even lay-skin="line" lay-size="lg">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>

    <tbody>
    <tr>
        <td>用户账号</td>
        <td>${user.userAccount}</td>

    </tr>
    <tr>
        <td>用户姓名</td>

        <td>${user.userName}</td>

    </tr>
    <tr>
        <td>用户手机号</td>

        <td>${user.mobileNumber}</td>
    </tr>
    <tr>
        <td>用户邮箱</td>

        <td>${user.email}</td>
    </tr>
    </tbody>
</table>
</div>

</body>
</html>

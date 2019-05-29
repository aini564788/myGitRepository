<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/28
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/ulogin" method="post">
    <div>
        用户名：<input type="text" name="uname" />
    </div>
    <div>
        密码：<input type="password" name="pwd" />
    </div>
    <div>
  <input type="submit" value="提交" />
</div>

</form>

</body>
</html>

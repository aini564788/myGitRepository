<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/20
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../includes/include.jsp"%>
</head>
<body>

<button type="button" class="layui-btn layui-btn-normal" id="test1">
    <i class="layui-icon">&#xe67c;</i>上传图片
</button>

<script>
    layui.use(['upload','layer'], function(){
        var upload = layui.upload;
        var layer=layui.layer;
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '${pageContext.request.contextPath}/uploadheadimage' //上传接口
            ,accept:"images"
            ,size:1024
            ,done: function(res){
                //上传完毕回调
                if(res.code==0){
                    layer.msg('success',{icon:6});

                }
            }
            ,error: function(){
                //请求异常回调
                layer.msg('error',{icon:5});
            }
        });
    });
</script>

</body>
</html>

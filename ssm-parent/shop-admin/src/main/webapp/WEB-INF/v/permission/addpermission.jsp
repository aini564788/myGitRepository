<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/3/11
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<form id="fm1" class="layui-form " action="${pageContext.request.contextPath}/permission/add/do"  method="post">
<%--
    <input type="hidden" name="_method" value="POST" />
--%>
    <div class="layui-form-item"  >
        <label class="layui-form-label">名称：</label>
        <div class="layui-input-inline">
            <input name="name" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item"  >
        <label class="layui-form-label">权限标志：</label>
        <div class="layui-input-inline">
            <input name="percode" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div id="url_id">
    <div class="layui-form-item"   >
        <label class="layui-form-label">路径URL：</label>
        <div class="layui-input-inline">
            <input name="url" lay-verify="required"  class="layui-input">
        </div>
    </div>

    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">type：</label>
        <div class="layui-input-inline">
            <input type="radio" name="type" value="menu" lay-filter="level"  title="menu" checked>

            <input type="radio" name="type" value="button" lay-filter="level"  title="button">
        </div>
    </div>

    <div class="layui-form-item" id="dvl1" style="display: none" >
        <label class="layui-form-label">上级路径：</label>
        <div class="layui-input-inline">
            <select id="spid"  name="parentid">
                <option value="0">----</option>

            </select>

        </div>
    </div>
    <div class="layui-form-item" >
        <label class="layui-form-label">提交：</label>
        <div class="layui-input-inline">
            <button class="layui-btn  layui-btn-lg" lay-submit lay-filter="sub">提交</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form','layer'], function(){
        var form = layui.form;

        //ajax查询数据库 查询所有角色追加到下拉框里面  注意最后面加一句  form.render() 重新渲染表格。
        $.ajax({
            url:'${pageContext.request.contextPath}/permission/menus/query',//一级菜单 parentId=0
            dataType:"json",
            type:'get',
            cache:false,
            success:function (obj) {
                var html="";
                for(var i=0;i<obj.length;i++){
                    html+="<option value='"+obj[i].perId+"'>"+obj[i].name+"</option>";

                }
                // alert(html);
                $("#spid").html(html);
                form.render();


            }

        })


        form.on('submit(sub)',function (obj) {
           // layer.msg('submit');



            return true;
        });

        var  s=$("#url_id").html();
        form.on('radio(level)',function (obj) {


            if(obj.value=='button'){//选择了二级菜单
                $("#dvl1").show();
                $("#url_id").empty();
                form.render();
            }
            else if(obj.value=='menu'){
                $("#dvl1").hide();
                $("#url_id").html(s);
                form.render();

            }


        })


    });

</script>

</body>
</html>

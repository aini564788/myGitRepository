<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/3/11
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<%--<shiro:hasPermission name="role:view">--%>
    <!--搜索内容区域-->
    <div class="layui-container">
        <div class="layui-row" style="margin-top: 10px">
            <div class="layui-col-xs3" style="margin-right: 20px">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">编号/权限名</label>
                    <div class="layui-input-block">
                        <input type="text" id="no" class="layui-input" placeholder="编号/名称">
                    </div>
                </div>
            </div>

            <div class="layui-col-xs3" style="margin-right: 20px">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">权限标志</label>
                    <div class="layui-input-block">
                        <select class="layui-input" id="fg">
                            <option value="">请选择</option>
                            <option value="menu">menu</option>
                            <option value="button">button</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs2">
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" onclick="searchData()"><i class="layui-icon layui-icon-search">搜索</i>
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--搜索内容区域结束-->
    <div class="layui-row" style="margin-top: 10px">
        <div class="layui-col-xs1" style="margin-right: 20px">

            <button class="layui-btn " onclick="searchData()">刷新</button>

        </div>
    </div>

    <table id="demo" lay-filter="test"></table>

<%--</shiro:hasPermission>
<shiro:lacksPermission name="role:view">
    <h1>您没有权限，请联系管理员！</h1>
</shiro:lacksPermission>--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showpermissions">查看权限</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
<script type="text/javascript">
    var table;
    var tableIns;
    var form;
    layui.use(['table','form','element'],function () {
        table=layui.table;
        form=layui.form;
        var element=layui.element;
        tableIns=table.render({
            elem: '#demo'
            ,height: 312
            ,url: '${pageContext.request.contextPath}/permissions' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'perId', title: 'ID', width:50, sort: true, fixed: 'left'}
                ,{field: 'name', title: 'name', width:150}
                ,{field: 'type', title: 'type', width:80}
                ,{field: 'url', title: 'url', width:80, sort: true}
                ,{field: 'percode', title: 'percode', width:150}
                ,{field: 'available', title: '有效/无效', width: 110, sort: true,templet:function (obj) {//obj表示当前的对象
                        var perId=obj.perId;
                        var f=obj.available;
                        var r="";
                        if(f){//1 非0数字都表示true
                            r = " <input type=\"checkbox\" value='" + perId + "' name=\"flag\" checked='checked' lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已激活|已禁用\">";
                        }else {
                            r = "<input type=\"checkbox\" name=\"flag\" value='" + perId + "'  lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已激活|已禁用\">";

                        }
                        return r;

                    }
                }
                ,{fixed: 'right', width: 205, align:'center', toolbar: '#barDemo'}

            ]]
        })


        //监听头工具栏事件     参考官网  https://www.layui.com/demo/
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据  通过.属性合一获取响应的值
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    window.location.href="${pageContext.request.contextPath}/permission/addpermission";
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('编辑 [id]：'+ checkStatus.data[0].perId);
                        window.location.href="${pageContext.request.contextPath}/user/modify?userId="+checkStatus.data[0].perId;

                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        var ids=[];
                        for(var i=0;i<checkStatus.data.length;i++){
                            ids.push(checkStatus.data[i].perId);
                        }
                        layer.msg('删除'+ids.join(","));
                        //批量删除
                        // alert(ids.toString()+typeof(ids.toString()));
                        deleBatch(ids.toString());


                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'checkrole'){
                layer.msg('赋予角色'+data.perId);

                loadRoles(data);




            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.msg("del:"+data.perId);
                    //向服务端发送删除指令

                    delById(data.perId,obj);

                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作'+data.perId);

                window.location.href="${pageContext.request.contextPath}/user/modify?userId="+data.perId;


            }
        });


        //给激活   按钮切换事件
        form.on('switch(active)', function (obj) {
            //obj.elem.checked 获得复选框的状态  true选中  false 不选中
            // alert(obj.elem.checked);
            //获取复选框的状态  true表示激活  false 表示禁用
            var status = obj.elem.checked;
            var id = obj.elem.value;//获取id值
            layer.open({
                content:'想好了吗？',
                btn:['取消','确定'],
                yes:function (index) {//第一个按钮触发的事件
                    // alert('取消');
                    obj.elem.checked=!status;//还原
                    //关闭弹框
                    layer.close(index);
                    form.render();//重新渲染表单
                },btn2:function (index) {//第2个按钮触发的事件
                    //alert('确定');
                    //发送ajax请求 改变状态
                    $.ajax({
                        url:'${pageContext.request.contextPath}/permission/active/update',
                        data:{"peId":id,"status":status},
                        dataType:'json',
                        type:'post',
                        cache:false,
                        success:function (obj) {
                            layer.close(index);
                            if(obj.code==0){
                                layer.msg('修改成功',{icon:1})
                            }else{
                                layer.msg('修改失败',{icon:5})
                            }
                            form.render();
                        },
                        error:function () {
                            obj.elem.checked=!status;//还原
                            layer.close(index);
                            layer.msg('服务器异常',{icon:5})
                            form.render();
                        }
                    })

                },cancel:function (index) {//点击右上角的×号触发的事件
                    //alert(111);
                    obj.elem.checked=!status;//还原
                    form.render();
                }



            })

        });




    })
    /**
     * 搜索用户
     * 重新加载表格
     */
    function  searchData() {
        //这里以搜索为例
        //获取搜多的内容
        var name=$("#no").val();
        var type=$("#fg").val();
        //alert(status+no);
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                name: name,
                type:type //…
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
    }

    /**
     * 批量删除的ajax
     */
    function  deleBatch(ids) {
        $.ajax({
            url:'${pageContext.request.contextPath}/permission/delete/batch',
            dataType:'json',
            data:{"ids":ids,_method:"DELETE"},
            type:'post',
            cache:false,
            success:function (res) {
                if(res.code==0){
                    //success

                    //layer.close(index);
                    //重新加载数据表格
                    searchData();
                    layer.msg('删除成功',{icon:1});


                }else{
                    layer.msg('删除失败'+res.msg,{icon:5});
                    //layer.close(index);
                }
            },
            error:function (res) {
                layer.msg('服务求安异常',{icon:5});
                // layer.close(index,{});
            }

        })


    }


    function  delById(id,obj) {
        $.ajax({
            url:'${pageContext.request.contextPath}/permission/delete/'+id,
            dataType:'json',
            type:'POST',
            data:{_method:"DELETE"},
            cache:false,
            success:function (res) {
                if(res.code==0){
                    //success
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.msg('删除成功',{icon:1});
                    layer.close(index,{});

                }else{
                    layer.msg('失败'+res.msg,{icon:5});
                    layer.close(index,{});
                }
            },
            error:function (res) {
                layer.msg('服务求安异常',{icon:5});
                layer.close(index,{});
            }

        })

    }

    /**
     * user是当前一行的数据  ：user.rids是当前用户所拥有的全部角色id
     * @param user
     */
    function loadRoles(user) {

        $("#userAccount").val(user.userAccount);
        $("#userId").val(user.userId);
        // 查询用户的所有角色
        $.get("${pageContext.request.contextPath}/role/allroles", null, function (arr) {
            //清空原来的数据
            $("#dv1").html("");
            for (var i = 0; i < arr.length; i++) {
                //让拥有角色的复选框选中
                if (user.rids.indexOf(arr[i].roleUkid) > -1) {//arr指所有角色 user.rids指该用户所拥有的所有角色
                    $("#dv1").append("<input type=\"checkbox\" class='layui-input-inline' name=\"rids\" value='" + arr[i].roleUkid + "' title=\"" + arr[i].roleName + "\" checked>");
                } else {
                    $("#dv1").append("<input type=\"checkbox\" class='layui-input-inline' name=\"rids\" value='" + arr[i].roleUkid + "' title=\"" + arr[i].roleName + "\">");
                }
            }
            form.render();
            layer.open({
                area: ['500px', '300px'],
                title: '用户角色编辑',
                type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
                content: $('#dvlay'), //这里content是一个普通的String
                btn: ['确认', '取消'],
                yes: function (index, layero) {
                    alert($("#fm1").serialize());
                    $.ajax({
                        url: "${pageContext.request.contextPath}/userroleedit",
                        //表单序列化 userId=2&userAccount=rose&rids=1&rids=2&rids=3&rids=4
                        data: $("#fm1").serialize(),
                        success: function (obj) {
                            if (obj) {
                                layer.msg("编辑权限成功", {icon: 6});
                                tableIns.reload();

                            } else {
                                layer.msg("编辑权限失败", {icon: 5});
                            }
                        }
                    })

                    layer.close(index);
                }, cancel: function () {
                }
            });
        })
    }

</script>

</html>

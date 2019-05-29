<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/14
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <script type="text/javascript">

    </script>

    <title>Title</title>
</head>
<body>


<%@include file="../includes/include.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/jquery-ztree/css/demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/jquery-ztree/css/zTreeStyle/zTreeStyle.css">
<script src="${pageContext.request.contextPath}/static/js/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>

<shiro:hasPermission name="role:view">
<!--搜索内容区域-->
<div class="layui-container">
    <div class="layui-row" style="margin-top: 10px">
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">编号/名称</label>
                <div class="layui-input-block">
                    <input type="text" id="no" class="layui-input" placeholder="编号/名称">
                </div>
            </div>
        </div>

        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">角色</label>
                <div class="layui-input-block">
                    <select class="layui-input" id="fg">
                        <option value="">请选择</option>
                        <option value="1">有效</option>
                        <option value="0">无效</option>
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

</shiro:hasPermission>
<shiro:lacksPermission name="role:view">
    <h1>您没有权限，请联系管理员！</h1>
</shiro:lacksPermission>
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
            ,url: '${pageContext.request.contextPath}/role/list' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'roleUkid', title: 'ID', width:150, sort: true, fixed: 'left'}
                ,{field: 'roleName', title: '角色名字', width:230}
                ,{field: 'createTime', title: '角色创建时间', width: 187}
                ,{field: 'status', title: '有效/无效', width: 130, sort: true,templet:function (obj) {//obj表示当前的对象
                    var roleUkid=obj.roleUkid;
                    var f=obj.status;
                    var r="";
                    if(f){
                        r = " <input type=\"checkbox\" value='" + roleUkid + "' name=\"flag\" checked='checked' lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已激活|已禁用\">";
                    }else {
                        r = "<input type=\"checkbox\" name=\"flag\" value='" + roleUkid + "'  lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已激活|已禁用\">";

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
           // alert(checkStatus+"--------->");
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    layer.open({
                        area: ['500px', '180px'],
                        title: '用户角色新增',
                        type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
                        content: $('#dvlay2'), //这里content是一个普通的String
                        btn: ['确认新增', '取消'],
                        yes: function (index, layero) {
                            //alert($("#fm1").serialize());
                            $.ajax({
                                url: "${pageContext.request.contextPath}/role/add",
                                //表单序列化
                                data: {"roleName":$("#add_roleName").val()},
                                success: function (obj) {
                                    if (obj.code==0) {
                                        tableIns.reload();
                                        layer.msg("新增成功", {icon: 6});

                                    } else {
                                        layer.msg("编辑权限失败", {icon: 5});
                                    }
                                }
                            });

                            layer.close(index);
                        }, cancel: function () {
                        }
                    });


                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('编辑 [id]：'+ checkStatus.data[0].roleUkid);
                       // ] window.location.href="${pageContext.request.contextPath}/role/modify?roleUkid="+checkStatus.data[0].roleUkid;
                        layer.msg('编辑操作'+checkStatus.data[0].roleUkid);
                        $.ajax({
                            url:'${pageContext.request.contextPath}/role/querybyid',
                            data:{"roleUkid":checkStatus.data[0].roleUkid},
                            async:false,
                            cache:false,
                            success:function (obj) {
                                $("#edit_roleUkid").val(obj.roleUkid);
                                $("#edit_roleName").val(obj.roleName);
                            }

                        });

                        layer.open({
                            area: ['500px', '260px'],
                            title: '用户角色编辑',
                            type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
                            content: $('#dvlay'), //这里content是一个普通的String
                            btn: ['确认', '取消'],
                            yes: function (index, layero) {
                                //alert($("#fm1").serialize());
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/role/editrole",
                                    //表单序列化
                                    data: $("#fm1").serialize(),
                                    success: function (obj) {
                                        if (obj.code==0) {
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



                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        var ids=[];
                        for(var i=0;i<checkStatus.data.length;i++){
                           ids.push(checkStatus.data[i].roleUkid);
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
            if(layEvent === 'showpermissions'){
                layer.msg('赋予角色'+data.roleUkid);

                //根据角色id查看该角色对应的所有权限   以树的形式展现
               showTree(data);

               //打开
                layer.open({
                    area: ['500px', '300px'],
                    title: '【'+data.roleName+'】所拥有权限',
                    type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
                    content: $('#dvlay3'), //这里content是一个普通的String
                    btn: ['修改权限', '取消'],
                    yes: function (index, layero) {
                       editPermission(index,data);


                        layer.close(index);
                    }, cancel: function () {
                    }
                });





            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.msg("del:"+data.roleUkid);
                    //向服务端发送删除指令

                    delById(data.roleUkid,obj);

                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作'+data.roleUkid);
                $.ajax({
                    url:'${pageContext.request.contextPath}/role/querybyid',
                    data:{"roleUkid":data.roleUkid},
                    async:false,
                    cache:false,
                    success:function (obj) {
                        $("#edit_roleUkid").val(obj.roleUkid);
                        $("#edit_roleName").val(obj.roleName);
                    }

                });

                layer.open({
                    area: ['500px', '260px'],
                    title: '用户角色编辑',
                    type: 1,//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。 若你采用layer.open({type: 1})方式调用，则type为必填项（信息框除外）
                    content: $('#dvlay'), //这里content是一个普通的String
                    btn: ['确认', '取消'],
                    yes: function (index, layero) {
                        //alert($("#fm1").serialize());
                        $.ajax({
                            url: "${pageContext.request.contextPath}/role/editrole",
                            //表单序列化
                            data: $("#fm1").serialize(),
                            success: function (obj) {
                                if (obj.code==0) {
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


            }
        });





    })
    /**
     * 搜索用户
     * 重新加载表格
     */
    function  searchData() {
        //这里以搜索为例
        //获取搜多的内容
        var no=$("#no").val();
        var status=$("#fg").val();
        //alert(status+no);
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                no: no
                ,status:status //…
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
            url:'${pageContext.request.contextPath}/role/deletebatch',
            dataType:'json',
            data:{"ids":ids},
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
                    layer.msg('失败'+res.msg,{icon:5});
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
            url:'${pageContext.request.contextPath}/role/'+id,
            type:'DELETE',
            cache:false,
            success:function (res) {
                if(res.code==0){
                    //success
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.msg('删除成功',{icon:1});

                }else{
                    layer.msg('失败'+res.msg,{icon:5});
                }
            },
            error:function (res) {
                layer.msg('服务求安异常',{icon:5});
            }

        })

    }

    /**
     * 显示权限树
     */
    function  showTree(obj) {
        var setting = {
            check: {
                enable: true,
                chkboxType:{ "Y" : "ps", "N" : "ps" },
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes;/*=[
            { id:1, pId:0, name:"随意勾选 1", open:true},
            { id:11, pId:1, name:"随意勾选 1-1", open:true},
            { id:111, pId:11, name:"随意勾选 1-1-1"},
            { id:112, pId:11, name:"随意勾选 1-1-2"},
            { id:12, pId:1, name:"随意勾选 1-2", open:true},
            { id:121, pId:12, name:"随意勾选 1-2-1"},
            { id:122, pId:12, name:"随意勾选 1-2-2"},
            { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
            { id:21, pId:2, name:"随意勾选 2-1"},
            { id:22, pId:2, name:"随意勾选 2-2", open:true},
            { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
            { id:222, pId:22, name:"随意勾选 2-2-2"},
            { id:23, pId:2, name:"随意勾选 2-3"}
        ];*/

        /**
         * ajax查询
         */

        $.ajax({
            url:'${pageContext.request.contextPath}/role/ztree',
            data:{"roleUkid":obj.roleUkid} ,
            async:false,
            success:function (obj) {

                zNodes=obj;
            }


        })
        //初始化zTree树
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);

    }


    function editPermission(index,data) {
        /**
         * 修改权限点击事件·
         */

            /**
             * 获取树
             */
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            /**
             * 获取所有选中的节点
             */
            var nodes = treeObj.getCheckedNodes(true);

            var aids="";
            for(var i=0;i<nodes.length;i++){
                aids+=nodes[i].id;
                if(i!=nodes.length-1){
                    aids+=",";
                }
            }

            //alert(ids);
            /**
             * ajax请求服务器，把 roleid和  所有的权限id传给服务器
             */
            $.ajax({
                url:'${pageContext.request.contextPath}/role/editpermission',
                data:{"rid":data.roleUkid,"aids":aids},
                type:"post",
                cache:false,
                success:function (obj) {
                   //1 alert(obj.code)
                    if(obj.code==0){
                     layer.msg("编辑权限成功", {icon: 6});
                    layer.close(index);
                    }
                    else{
                        layer.msg("编辑权限失败", {icon: 5});
                        layer.close(index);
                    }
                }
            })








    }
</script>



<!--编辑用户角色的表单开始-->
<div style="display: none;margin-top: 10px;width: 480px" id="dvlay">
    <form class="layui-form"  id="fm1"  action="${pageContext.request.contextPath}/role/domodify" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">用户序号</label>
            <div class="layui-input-block">
                <input type="text" name="roleUkid" id="edit_roleUkid"  readonly="readonly" autocomplete="off"
                       class="layui-input layui-bg-gray" id="f0">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">角色名</label>
            <div class="layui-input-block">
                <input type="text"  lay-verify="required"  name="roleName" id="edit_roleName"  autocomplete="off" class="layui-input">
            </div>
        </div>

    </form>
</div>
<!--编辑用户角色的表单结束-->


<!--用户角色的表单新增开始-->
<div style="display: none;margin-top: 10px;width: 480px" id="dvlay2">
    <form class="layui-form" id="fm2" action="${pageContext.request.contextPath}/role/modify" method="post">

        <div class="layui-form-item">
            <label class="layui-form-label">角色名</label>
            <div class="layui-input-block">
                <input type="text"  lay-verify="required"  name="roleName" id="add_roleName"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>
</div>
<!--编辑用户角色的表单结束-->
<div style="display: none;margin-top: 10px;width: 480px" id="dvlay3">
<ul id="treeDemo" class="ztree"></ul>
</div>
</html>

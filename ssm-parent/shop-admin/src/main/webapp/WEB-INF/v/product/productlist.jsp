<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/14
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>


    <title>Title</title>
</head>
<body>


<%@include file="../includes/include.jsp"%>

<script type="text/javascript">
    $(function () {
        //ajax初始化所有品牌信息
        $.get('${pageContext.request.contextPath}/brand',function (obj) {

            var str="<option value=\"\">请选择</option>";
            for(var i=0;i<obj.length;i++){
                str+=" <option value=\""+obj[i].brandId+"\">"+obj[i].brandName+"</option>";
            }
            $("#brandId").html(str);
            // form.render();

        });

        //初始化一级目录
        $.get('${pageContext.request.contextPath}/categories',function (obj) {

            var str="<option value=\"\">请选择</option>";
            for(var i=0;i<obj.length;i++){
                str+=" <option value=\""+obj[i].categoryId+"\">"+obj[i].categoryName+"</option>";
            }
            $("#firstCategoryId").html(str);
            // form.render();

        });

        //一级目录触发改变事件
        $("#firstCategoryId").change(function () {
            //获取一级目录的id
            var id=$(this).val();
            //初始化二级目录
            if(id==''){
                $("#secondCategoryId").html("");
                form.render();
                return ;
            }
            //初始化二级目录
            $.get('${pageContext.request.contextPath}/categories/second/'+id,function (obj) {

                var str="";//<option value="">请选择</option>
                for(var i=0;i<obj.length;i++){
                    str+=" <option value=\""+obj[i].categoryId+"\">"+obj[i].categoryName+"</option>";
                }
                $("#secondCategoryId").html(str);
                // form.render();

            });
        })

    })
</script>
<!--搜索内容区域-->

<div class="layui-container">

    <div class="layui-row" style="margin-top: 10px">
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">商品名称</label>
                <div class="layui-input-block">
                    <input type="text" id="productName" class="layui-input" placeholder="商品名称">
                </div>
            </div>
        </div>

        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">品牌</label>
                <div class="layui-input-block">
                    <select class="layui-input" id="brandId">
                       <%-- <option value="">请选择</option>
                        <option value="1">有效</option>
                        <option value="0">无效</option>--%>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-xs3" style="">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">一级目录</label>
                <div class="layui-input-block">
                    <select class="layui-input" id="firstCategoryId">
                        <%--<option value="">请选择</option>
                        <option value="1">有效</option>
                        <option value="0">无效</option>--%>
                    </select>

                </div>
            </div>
        </div>
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">二级目录</label>
                <div class="layui-input-block">
                    <select class="layui-input" id="secondCategoryId">
                      <%--  <option value="">请选择</option>
                        <option value="1">有效</option>
                        <option value="0">无效</option>--%>
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
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="uoloadimages">上传主图</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showProductDes">商品介绍</a>
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
            ,url: '${pageContext.request.contextPath}/product/query/page' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,done:function(res,curr,count) {
                hoverOpenImg();//显示大图
            }
            ,cols: [[ //表头
                 {type: 'checkbox'}
                ,{field: 'productId', title: '商品编号', width:50, sort: true}
                ,{field: 'picUrl', title: '商品图片', width:100,templet:function (obj) {
                    var picUrl=obj.picUrl;
                    return "<img style='height:30px;width:40px;'  src='${pageContext.request.contextPath}/"+picUrl+"' class='' />" ;
                    }}
                ,{field: 'productName', title: '商品名字', width:80}
                ,{field: 'brandName', title: '品牌名字', width:80}

                ,{field: 'barCode', title: '商品国条码', width:80}
                ,{field: 'price', title: '商品价钱', width:80}
                ,{field: 'publishStatus', title: '上架/下架', width: 110, sort: true,templet:function (obj) {//obj表示当前的对象
                        var productId=obj.productId;
                        var f=obj.publishStatus;
                        var r="";
                        if(f==1){
                            r = " <input type=\"checkbox\" value='" + productId + "' name=\"flag\" checked='checked' lay-skin=\"switch\" lay-filter=\"onSale\" lay-text=\"已上架|已下架\">";
                        }else {
                            r = "<input type=\"checkbox\" name=\"flag\" value='" + productId + "'  lay-skin=\"switch\" lay-filter=\"onSale\" lay-text=\"已上架|已下架\">";
                        }
                        return r;
                    }
                },
            {field: 'auditStatus', title: '审核', width: 110, sort: true,templet:function (obj) {//obj表示当前的对象
                    var productId=obj.productId;
                    var f=obj.auditStatus;
                    var r="";
                    if(f==1){
                        r = " <input type=\"checkbox\" value='" + productId + "' name=\"flag\" checked='checked' lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已审核|未审核\">";
                    }else {
                        r = "<input type=\"checkbox\" name=\"flag\" value='" + productId + "'  lay-skin=\"switch\" lay-filter=\"active\" lay-text=\"已审核|未审核\">";

                    }
                    return r;

                }
            }
                ,{fixed: 'right', width: 270, align:'center', toolbar: '#barDemo'}

            ]]
        })
        //给上架   按钮切换事件
        form.on('switch(onSale)', function (obj) {
            //obj.elem.checked 获得复选框的状态  true选中  false 不选中
            // alert(obj.elem.checked);
            //获取复选框的状态  true表示上架  false 表示下架
            var saleStatus = obj.elem.checked;
            var productId = obj.elem.value;//获取id值
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
                        url:'${pageContext.request.contextPath}/product/publish/update',
                        data:{"productId":productId,"saleStatus":saleStatus},
                        dataType:'json',
                        type:'post',
                        cache:false,
                        success:function (obj) {
                            layer.close(index);
                            if(obj.code==0){
                                layer.msg('上架状态修改成功',{icon:1})
                            }else{
                                layer.msg('上架状态修改失败',{icon:5})
                            }
                            form.render();
                        },
                        error:function () {
                            obj.elem.checked=!audit;//还原
                            layer.close(index);
                            layer.msg('服务器异常',{icon:5});
                            form.render();
                        }
                    })

                },cancel:function (index) {//点击右上角的×号触发的事件
                    //alert(111);
                    obj.elem.checked=!audit;//还原
                    form.render();
                }
            })
        });
        //给审核   按钮切换事件
        form.on('switch(active)', function (obj) {
            //obj.elem.checked 获得复选框的状态  true选中  false 不选中
            // alert(obj.elem.checked);
            //获取复选框的状态  true表示激活  false 表示禁用
            var audit = obj.elem.checked;
            var productId = obj.elem.value;//获取id值
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
                        url:'${pageContext.request.contextPath}/product/audit/update',
                        data:{"productId":productId,"audit":audit},
                        dataType:'json',
                        type:'post',
                        cache:false,
                        success:function (obj) {
                            layer.close(index);
                            if(obj.code==0){
                                layer.msg('审核状态修改成功',{icon:1})
                            }else{
                                layer.msg('审核状态修改失败',{icon:5})
                            }
                            form.render();
                        },
                        error:function () {
                            obj.elem.checked=!audit;//还原
                            layer.close(index);
                            layer.msg('服务器异常',{icon:5});
                            form.render();
                        }
                    })

                },cancel:function (index) {//点击右上角的×号触发的事件
                    //alert(111);
                    obj.elem.checked=!audit;//还原
                    form.render();
                }
            })
        });



        //监听头工具栏事件     参考官网  https://www.layui.com/demo/
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据  通过.属性合一获取响应的值
            // alert(checkStatus+"--------->");
            switch(obj.event){
                case 'add':
                    layer.msg('添加');

                    location.href='${pageContext.request.contextPath}/product/productadd';

                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.msg('编辑 [id]：'+ checkStatus.data[0].roleUkid);
                        window.location.href="${pageContext.request.contextPath}/role/modify?roleUkid="+checkStatus.data[0].roleUkid;

                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        var ids=[];
                        for(var i=0;i<checkStatus.data.length;i++){
                            ids.push(checkStatus.data[i].productId);
                        }
                        layer.msg('删除'+ids.join(","));
                        //批量删除
                        // alert(ids.toString()+typeof(ids.toString()));
                        deleBatch(ids.toString());


                    }
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            layer.msg('商品介绍id');
            if(layEvent === 'uoloadimages'){

                window.location.href="${pageContext.request.contextPath}/product/images/"+data.productId;

            }
            if(layEvent === 'showProductDes'){
                layer.msg('商品介绍id');
                layer.msg('商品介绍id'+data.productId);

                //根据角色id查看该角色对应的所有权限   以树的形式展现
                var pid=data.productId;
                window.location.href='${pageContext.request.contextPath}/product/desc/'+pid;

            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    layer.msg("del:"+data.productId);
                    //向服务端发送删除指令
                    delById(data.productId,obj);
                    layer.close(index);
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作'+data.productId);
                $.ajax({
                    url:'${pageContext.request.contextPath}/role/querybyid',
                    data:{"pid":data.productId},
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
        var productName=$("#productName").val();
        var brandId=$("#brandId").val();
        var   firstCategoryId=$("#firstCategoryId").val();
        var  secondCategoryId=$("#secondCategoryId").val();
        //alert(status+no);
        tableIns.reload({
            where: { //设定异步数据接口的额外参数，任意设
                productName: productName
                ,brandId:brandId //…
                ,firstCategoryId:firstCategoryId
                ,secondCategoryId:secondCategoryId
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
            url:'${pageContext.request.contextPath}/product/delete/batch',
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
            url:'${pageContext.request.contextPath}/product/delete/'+id,
            dataType:'json',
            type:'post',
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
     * 显示大图
     */
    function hoverOpenImg(){
        var img_show = null; // tips提示
        $('td img').hover(function(){
            //alert($(this).attr('src'));
            var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:130px;' />";
            img_show = layer.tips(img, this,{
                tips:[2, 'rgba(41,41,41,.5)']
                ,area: ['160px']
            });
        },function(){
            layer.close(img_show);
        });
        //$('td img').attr('style','max-width:70px');
    }






    function showProductDes(data) {
        var pid=data.productId;
        window.location.href='${pageContext.request.contextPath}/product/desc/'+pid;

    }

</script>



</html>

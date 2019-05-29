<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/3/4
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品添加</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/jquery-ztree/css/demo.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/jquery-ztree/css/zTreeStyle/zTreeStyle.css">
<script src="${pageContext.request.contextPath}/static/js/jquery-ztree/js/jquery.ztree.all-3.5.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/wangEditor/wangEditor.css">
<script src="${pageContext.request.contextPath}/static/js/wangEditor/wangEditor.js"></script>


<style type="text/css">
    .layui-form-select dl { max-height:180px; }
   #secondCategoryId2 .layui-form-select dl { max-height:120px; }
</style>

<div class="layui-container" style="margin-top: 5px">

    <form class="layui-form" id="addform" lay-filter="addform" action="${pageContext.request.contextPath}/product/add/do" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-block">
                <input type="text" name="productName"  id="productName"   lay-verify="required" placeholder="商品名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">国条码</label>
            <div class="layui-input-block">
                <input type="text" name="barCode" id="barCode" required lay-verify="required" placeholder="国条码" autocomplete="off" class="layui-input">
            </div>
            <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
        </div>
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">品牌</label>
                <div class="layui-input-block">
                    <select class="layui-input" lay-verify="required" name="brandId" id="brandId">
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">一级目录</label>
            <div class="layui-input-block">
                <select class="layui-input" lay-verify="required" lay-filter="change" name="firstCategoryId" id="firstCategoryId">

                </select>

            </div>
        </div>
        <div class="layui-form-item layui-form-text" id="secondCategoryId2">
            <label class="layui-form-label">二级目录</label>
            <div class="layui-input-block">
                <select class="layui-input" lay-verify="required"  name="secondCategoryId" id="secondCategoryId">

                </select>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品价格</label>
            <div class="layui-input-block">
                <input type="text" name="price" value="" id="price" lay-verify="required" autocomplete="off"
                       placeholder="商品价格" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">商品重量（克g）</label>
            <div class="layui-input-block">
                <input type="text"    placeholder="商品重量（g）"  name="weight" value="" id="weight" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品长度（毫米mm）</label>
            <div class="layui-input-block">
                <input type="text"    placeholder="商品长度（毫米mm）"  name="length" value="" id="length" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品高度（毫米mm）</label>
            <div class="layui-input-block">
                <input type="text"    placeholder="商品高度（毫米mm）"  name="height" value="" id="height" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">商品宽度（毫米mm）</label>
            <div class="layui-input-block">
                <input type="text"    placeholder="商品宽度（毫米mm）"  name="width" value="" id="width" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">商品介绍</label>
            <div class="layui-input-block">
                    <textarea style="display: none" id="descript" name="descript"></textarea>
                <div id="editor">

                </div>

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
    var form,layer,laydate;

    layui.use([ 'form', 'laydate','layer' ],
        function() {
                form = layui.form;
                layer = layui.layer;
                laydate = layui.laydate;
            //重新渲染表哥
            form.render();



    //ajax初始化所有品牌信息
    $.get('${pageContext.request.contextPath}/brand',function (obj) {

        var str="<option value=\"\">请选择</option>";
        for(var i=0;i<obj.length;i++){
            str+=" <option value=\""+obj[i].brandId+"\">"+obj[i].brandName+"</option>";
        }
        $("#brandId").html(str);
       form.render();

    });

    //初始化一级目录
    $.get('${pageContext.request.contextPath}/categories',function (obj) {

        var str="<option value=\"\">请选择</option>";
        for(var i=0;i<obj.length;i++){
            str+=" <option value=\""+obj[i].categoryId+"\">"+obj[i].categoryName+"</option>";
        }
        $("#firstCategoryId").html(str);
        form.render();

    });

    //一级目录触发改变事件
            form.on('select(change)',function (obj) {//用layui特有的方式  不要使用 jq的方式  在form表单里  不好用

                //获取一级目录的id
                var id=obj.value;
                //初始化二级目录
                if(id==''){
                    $("#secondCategoryId").html("");
                    form.render();
                    return ;
                }
                $.get('${pageContext.request.contextPath}/categories/second/'+id,function (obj) {

                    var str="";//<option value="">请选择</option>
                    for(var i=0;i<obj.length;i++){
                        str+=" <option value=\""+obj[i].categoryId+"\">"+obj[i].categoryName+"</option>";
                    }
                    $("#secondCategoryId").html(str);
                    form.render();
             })

            })

            form.on('submit(addform)',function (obj) {
                alert(11);


                // 获取内容
                var content = editor.txt.html();
                alert(content);
                return  true;
            })



        })


    var E = window.wangEditor;
    var editor = new E('#editor');

    var icons;
    var icons2;
 //加載表情的json串
    $.ajax({
        url:'${pageContext.request.contextPath}/static/conf/icons.json',
        async:false,
        success:function (obj) {
            icons=obj;
        }
    })
    $.ajax({
        url:'${pageContext.request.contextPath}/static/conf/icons2.json',
        async:false,
        success:function (obj) {
            icons2=obj;
        }
    })

    //editor.customConfig.uploadImgShowBase64 = true;
    this.editor.customConfig.emotions = [
        {
            title: '表情1',
            type: 'image',
            content: icons
        },
        {
            title: '表情2',
            type: 'image',
            content: icons2
        }
    ]


   // 自定义菜单配置
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'table',  // 表格
        'video',  // 插入视频
        'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    // 隐藏“网络图片”tab
    editor.customConfig.showLinkImg = true;
    editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/product/upload/img';  // 上传图片到服务器
    // 3M
   // editor.customConfig.uploadImgMaxSize = 20 * 1024 * 1024;
    // 限制一次最多上传 5 张图片
    editor.customConfig.uploadImgMaxLength = 10;
    // 自定义文件名
    editor.customConfig.uploadFileName = 'editorFile';
    // 将 timeout 时间改为 3s
    editor.customConfig.uploadImgTimeout = 5000;

    editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {

        },
        success: function (xhr, editor, result) {

        },
        fail: function (xhr, editor, result) {

        },
        error: function (xhr, editor) {

        },
        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {

            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url = result.data[0];
            insertImg(url);
            var content = editor.txt.html();
            // result 必须是一个 JSON 格式字符串！！！否则报错

            //把字符串放到文本域里面。
            $("#descript").html(content);

        }
    }

    editor.create();




</script>
</body>
</html>

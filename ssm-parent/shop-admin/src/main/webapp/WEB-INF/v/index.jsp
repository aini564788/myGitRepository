<%--
Created by IntelliJ IDEA.
User: tomcat
Date: 2019/2/11
Time: 22:34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
	</head>

	<%@include file="./includes/include.jsp"%>
	<script type="text/javascript">
		$(function () {
				$.ajax({
					url:"${pageContext.request.contextPath}/queryCurrentUser",
					type:"post",
					dataType:"json",
					async:false,
					success:function (obj) {
						var user=obj;
						$("#img").attr("src","${pageContext.request.contextPath}/"+user.headimageurl+"");
						$("#theUser").append(user.userAccount);
					}
				})

		})


	</script>
	<body class="layui-layout-body">

	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">layui 后台布局</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item "><a href="#">控制台</a></li>
				<li class="layui-nav-item lef_menu"><a href="#">商品管理</a></li>
				<li class="layui-nav-item lef_menu"><a href="#">用户</a></li>
				<li class="layui-nav-item">
					<a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd class="lef_menu" pid="11"><a href="#">邮件管理</a></dd>
						<dd class="lef_menu" pid="10"><a href="#">修改密码</a></dd>
						<dd class="lef_menu" pid="9"><a href="#">授权管理</a></dd>
					</dl>
				</li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item">
					<a href="javascript:;" id="theUser" >
						<img src="" id="img" class="layui-nav-img">
					</a>
					<dl class="layui-nav-child">
					<dd class="lef_menu" url="${pageContext.request.contextPath}/user/headImage"  pid="8"><a href="#">修改头像</a></dd>
					<dd class="lef_menu" pid="7" url=><a href="${pageContext.request.contextPath}/user/modifyPassword">修改密码</a></dd>
				</dl>
				</li>
				<li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout">退了</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree"  lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed">
						<a class="" href="javascript:;" >用户管理</a>
						<dl class="layui-nav-child">
							<dd class="lef_menu" pid="6" url="${pageContext.request.contextPath}/user/userlist"><a  href="javascript:;">用户列表</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">角色管理</a>
						<dl class="layui-nav-child">
							<dd class="lef_menu" url="${pageContext.request.contextPath}/role/rolelist" pid="5"><a href="javascript:;">角色列表</a></dd>
							<dd class="lef_menu" pid="50"><a href="javascript:;">权限树</a></dd>
						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">权限管理</a>
						<dl class="layui-nav-child">
							<dd class="lef_menu" url="${pageContext.request.contextPath}/permission/permissionlist" pid="4"><a href="javascript:;">权限列表</a></dd>

						</dl>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;">商品管理</a>
						<dl class="layui-nav-child">
							<dd class="lef_menu" pid="3" url="${pageContext.request.contextPath}/product/productlist"> <a href="javascript:;">商品列表</a></dd>

						</dl>
					</li>
					<li class="layui-nav-item lef_menu " pid="1"><a href="#">云市场</a></li>
					<li class="layui-nav-item lef_menu" pid="2"><a href="#">发布商品</a></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<%--

lay-filter	任意字符	事件过滤器（公用属性），主要用于事件的精确匹配，跟选择器是比较类似的。
lay-allowClose	true	针对于Tab容器，是否允许选项卡关闭。默认不允许，即不用设置该属性--%>
			<div class="layui-tab" lay-allowClose="true"  lay-filter="demo" style="width: 100%;height: 90%">
				<ul class="layui-tab-title"></ul>
				<div class="layui-tab-content" style="width: 99%;height: 98%"></div>
			</div>





		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>
	<script>
		var ids=[];//存放已经打开的所有选项卡的id
        var element;
        //JavaScript代码区域
        layui.use('element', function(){
            element = layui.element;

/*

            element.on('tabDelete(demo)', function(data){
                console.log(this); //当前Tab标题所在的原始DOM元素
                console.log(data.index); //得到当前Tab的所在下标
                console.log(data.elem); //得到当前的Tab大容器
            });
*/


        });

        //派发事件 点击可以生成选项卡
        $(".lef_menu").click(function () {
            console.log($(this).text());
            var id=$(this).attr("pid");
			var title=$(this).text();
			var url=$(this).attr("url");
			if(ids.indexOf(id)!=-1){//说明存在该选项卡  则切换到该选项卡

                changeTab(id);
			}else {//找不到该选项卡
                tabAdd(id, title, url);//生成个选项卡
                changeTab(id);
            }
            element.render();
        })
        /**
		 * function函数 创建一个选项卡
		 * element模块
         */
        function  tabAdd(id,title,url) {
            //新增一个Tab项
            element.tabAdd('demo', {
                title: title //用于演示
                ,content: '<iframe frameborder="0" scrolling="auto" width="100%" height="100%" src="'+url+'"></iframe>'
                ,id: id //实际使用一般是规定好的id，这里以时间戳模拟下
            })
			ids.push(id);

        }

        /**
		 * 切换选项卡
         * @param id
         */
        function changeTab(id) {
			element.tabChange('demo',id);
        }

        /**
		 * 选项卡被删除事件  当选项卡被删除的时候 从数组里面删除 id
         */
        $(".layui-tab").on("click",function(e){
            if($(e.target).is(".layui-tab-close")){
             console.log(e.target.nodeName+"============"+$(e.target).parent().attr("lay-id"))
				//数组里面删除该选项卡id
				var id=$(e.target).parent().attr("lay-id");
				for(var i=0;i<ids.length;i++){
				    if(id==ids[i]){
				        //删除id
				        ids.splice(i);

					}
				}
            }
        })



	</script>
	</body>

</html>

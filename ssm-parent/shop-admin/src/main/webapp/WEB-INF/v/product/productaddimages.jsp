<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/3/11
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>

</head>

<body>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/dropzone/basic.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/js/dropzone/dropzone.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/dropzone/dropzone.js"></script>

<div id="dropz" class="dropzone"></div>


</body>
<script>
    Dropzone.autoDiscover = false;//防止报"Dropzone already attached."的错误
    var myDropzone = new Dropzone("#dropz", {
        url: "${pageContext.request.contextPath}/product/images/modify", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "file", // 默认为file
        maxFiles: 9,// 一次性上传的文件数量上限
        maxFilesize: 50, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        //addRemoveLinks: true,
        parallelUploads: 9,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传9个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        params:{"id":${id}},
        init: function () {
            this.on("addedfile", function (file) {
                // 上传文件时触发的事件
            });
            this.on("success", function (file, data) {
                // 上传成功触发的事件
            });
            this.on("error", function (file, data) {
                // 上传失败触发的事件
            });
            this.on("removedfile", function (file) {
                // 删除文件时触发的方法
            });
        }
    });
</script>
</html>

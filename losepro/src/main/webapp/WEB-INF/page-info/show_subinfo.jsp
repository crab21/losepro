<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>


<%--
<br><br><br>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form">
                <div class="form-group">
                    </label><input type="text" class="form-control" id="articletitle"  placeholder="请输入标题"/>
                </div>
            </form>
        </div>
    </div>
</div>
--%>

<script type="text/javascript">
    //    调用编辑器
    var testEditor;
    $(function () {


        testEditor = editormd("test-editormd", {
            width: "90%",
            height: 740,
            path: "<%=request.getContextPath()%>/staticResource/plugin/editorMd/lib/",
//            theme: "dark",
//            previ ewTheme: "dark",
//            editorTheme: "pastel-on-dark",
            codeFold: true,
            //syncScrolling : false,
            saveHTMLToTextarea: true,    // 保存 HTML 到 Textarea
            searchReplace: true,
            //watch : false,                // 关闭实时预览
            htmlDecode: "style,script,iframe|on*",            // 开启 HTML 标签解析，为了安全性，默认不开启
            //toolbar  : false,             //关闭工具栏
            //previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
            emoji: true,
            taskList: true,
            tocm: true,         // Using [TOCM]
//            tex: true,                   // 开启科学公式TeX语言支持，默认关闭
//            flowChart: false,             // 开启流程图支持，默认关闭
//            sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],

        });
    });
</script>

<!--编辑器-->

    <div class="form-group" style="width: 50%;margin:0 auto">
        <input type="text" style="width: 200px;display: inline-block;float: left" class="form-control"  name="artTitle" id="artTitle" placeholder="请输入标题">
        <input type="text" style="width: 200px;display: inline-block;float: right" class="form-control"  name="artType" id="artType" placeholder="新建标签">
    </div>
    <input type="button" id="btns" class="btn" value="ok"/>
    <div id="test-editormd">
        <textarea style="display:none;" id="artDetaInfo" name="artDetaInfo"></textarea>
    </div>



<script src="<%=request.getContextPath()%>/staticResource/plugin/editorMd/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#btns").click(function () {
            var artTitle=$("#artTitle").val();
            var artType=$("#artType").val();
            alert(artTitle)
            $.ajax({
                url:"sub/subinfo",
                type:"POST",
                dataType:"json",
                data:{
                    "artTitle":artTitle,
                    "artDetaInfo":testEditor.getMarkdown(),
                    "artType":artType
                },success:function(data) {
                    alert(data);
                }
            });
        });
    });
</script>


<script type="text/javascript">
    $(function() {
        var testEditor = editormd("test-editormd", {
            width: "90%",
            height: 640,
            markdown : "",
            path : '<%=request.getContextPath()%>/staticResource/plugin/editorMd/lib/',
            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为 true
            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为 true
            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为 true
            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为 0.1
            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为 #fff
            imageUpload : true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
            imageUploadURL : "sub/subImageFile",
            crossDomainUpload : true,
            uploadCallbackURL : "editorMd/upload_callback.html"

            /*
             上传的后台只需要返回一个 JSON 数据，结构如下：
             {
             success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
             message : "提示的信息，上传成功或上传失败及错误信息等。",
             url     : "图片地址"        // 上传成功时才返回
             }
             */
        });
    });
</script>
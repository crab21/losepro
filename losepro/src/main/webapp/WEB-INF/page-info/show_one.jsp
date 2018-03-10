<%@ page contentType="text/html;charset=UTF-8" language="java" %>


            <head>
                <script src="<%=request.getContextPath()%>/other/md/lib/marked.min.js"></script>
                <script src="<%=request.getContextPath()%>/other/md/lib/prettify.min.js"></script>
                <script src="<%=request.getContextPath()%>/plugin/editorMd/jquery.min.js"></script>
                <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
                <link rel="stylesheet" href="<%=request.getContextPath()%>/css/editormd.preview.css"/>
            </head>

            <div class="container">
                <div class="row clearfix">

                    <div class="col-md-1 column"></div>
                    <div class="col-md-9 column">
                        <h3 id="hh"></h3>
                        <div id="test-editormd-view2">
                             <textarea id="append-test" style="display:none;">

                                 <%--${blogInfoDetails.artDetaInfo}--%>

                            </textarea>
                        </div>

                    </div>
                    <div class="col-md-1 column">
                    </div>
                </div>
            </div>

            <script type="text/javascript">
                $(function () {
                    var articleId = '<%=request.getAttribute("articleId")%>';
                    var url = '<%=request.getContextPath()%>/show/'+articleId;
                    $.post(url,{
                        artId:articleId
                    },function (markdown) {
                        $("#hh").html(markdown.artTitle);
                        markdown = markdown.artDetaInfo;

                        var testEditormdView2;
                        <%--var markdown = ${blogInfoDetails.artDetaInfo};--%>
//                    $("#test-editormd-view").html(markdown);
                        testEditormdView2 = editormd.markdownToHTML("test-editormd-view2", {
                            markdown        : markdown ,//+ "\r\n" + $("#append-test").text(),
                            htmlDecode      : "style,script,iframe,html",  // you can filter tags decode
                            emoji           : true,
                            taskList        : true,
                            tex             : false,  // 默认不解析
                            flowChart       : false,  // 默认不解析
                            sequenceDiagram : false,  // 默认不解析
                        });
                    },"json");
                });
            </script>
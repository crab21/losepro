<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><tiles:insertAttribute name="title"/></title>
    <tiles:insertAttribute name="header"/>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1"><span
                            class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                            class="icon-bar"></span><span class="icon-bar"></span></button>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/welcome">MainPage</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="">
                            <a href="<%=request.getContextPath()%>/showmore">文章列表</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/showinfo">写文章</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">查看分类<strong
                                    class="caret"></strong></a>
                            <ul class="dropdown-menu" id="type" style="text-align: center">

                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input class="form-control" type="text"/>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="<%=request.getContextPath()%>/loginpage>">登录/注册</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">关于作者</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">个人中心</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">查看帮助</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
            <br><br><br>
            <tiles:insertAttribute name="body" ignore="true"/>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var $url = '<%=request.getContextPath()%>/show/findType';
        $.post($url, {
            artId: "artType"
        }, function (data) {
            var $ele = $("#type");
            for (var i = 0; i < data.length; ++i) {
                var $artType = data[i].artType;
                var $content = "<a href=\"<%=request.getContextPath()%>/show/findType?artType=" + $artType + "\">" + $artType + "</a>";
                var $lis = "<li>" + $content + "</li>";
                $ele.append($lis);
            }
        }, "json");
    });
</script>

</body>
</html>
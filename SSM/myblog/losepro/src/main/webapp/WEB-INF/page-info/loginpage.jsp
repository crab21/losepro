<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--

    -->

<div class="col-md-8 col-md-offset-2">
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="col-md-12" style="text-align:center"><h2>用户登陆界面</h2></div>
    <br>
    <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/login" id="form1" method="post">
        <div class="form-group">
            <label class="col-sm-4 control-label">用户名：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入名字">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-4 control-label">密码：</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>
        <!-- 提供额外的视觉效果，标识一组按钮中的原始动作 -->
        <div class="col-md-6" style="text-align:right">
            <button type="button" class="btn btn-primary" id="loginIn">登陆</button>
        </div>
        <div class="col-md-6" style="text-align:left">
            <button type="button" class="btn btn-success" id="resetIn">重置</button>
        </div>
        <!-- 表示一个成功的或积极的动作 -->

    </form>
    <script type="text/javascript">
        $(function () {
            $("#loginIn").click(function () {
                $("#form1").submit();
            });
        });
    </script>
</div>
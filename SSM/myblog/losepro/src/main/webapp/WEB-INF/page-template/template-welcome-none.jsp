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
            <tiles:insertAttribute name="body" ignore="true"/>
        </div>
    </div>
</div>
</body>
</html>
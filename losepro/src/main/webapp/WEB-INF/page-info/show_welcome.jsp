<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="jumbotron">
    <h1>
        Hello, world!
    </h1>
    <p>
        This is a template for a simple marketing or informational website. It includes a large callout called the hero
        unit and three supporting pieces of content. Use it as a starting point to create something more unique.
    </p>
    <p>
        <a class="btn btn-primary btn-large" href="#">Learn more</a>
    </p>
</div>

<div class="row clearfix">
    <div class="col-md-6 column">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    按时间
                </h3>
            </div>

            <c:forEach items="${briefInfo}" var="briefInfoOne">
                <div class="panel-footer">
                <a href="#">${briefInfoOne.artTitle}</a>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-md-6 column">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    按阅读量
                </h3>
            </div>


            <c:forEach items="${briefInfoOfEvalReadNum}" var="briefInfoOne">
                <div class="panel-footer">
                    <a href="#">${briefInfoOne.artTitle} &nbsp;&nbsp;&nbsp;</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>




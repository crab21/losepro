<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script type="text/javascript" src="<%=request.getContextPath()%>/staticResource/pageStatic/blog/js/showmore.js"></script>
<div class="row clearfix">
    <div class="col-md-2 column"></div>
    <div class="col-md-8 column" style="height: 80%">
        <c:if test="${!empty blogBriefInfo}">
            <c:forEach items="${blogBriefInfo}" var="blogInfo" step="1">
                <blockquote>
                    <a href="<%=request.getContextPath()%>/show/${blogInfo.artId}" style="text-decoration: none">
                        <p>
                                ${blogInfo.artTitle}
                        </p>
                        <small>${blogInfo.artBrief} <cite>${blogInfo.artTime}</cite></small>
                    </a>
                </blockquote>
            </c:forEach>

            <div style="text-align: center;width: 100%;">
                <input type="hidden" value="${fn:length(blogBriefInfo)}" id="blogSize"/>
                <input type="hidden" value="${page.pageNumber}" id="pageNumber">
                <input type="hidden" value="${page.total}" id="pageTotal">
                <input type="hidden" value="${artType}" id="artType" name="artType">

                <a href="<%=request.getContextPath()%>/showmore">首页</a>
                <c:set var="pageFlagBef" scope="page" value="0"/>
                <a href="<%=request.getContextPath()%>/showmore?pageFlag=${pageFlagBef}&pageForeId=${blogBriefInfo[0].id}&pageNumber=${page.pageNumber-1}&artType=${artType}" id="beforePage">上一页</a>

                    <%--todo 有待处理的页数 --%>

                <c:set var="pageFlagNext" scope="page" value="1"/>
                <c:set var="nextPageId" scope="page" value="${fn:length(blogBriefInfo)}"/>
                <a href="<%=request.getContextPath()%>/showmore?pageFlag=${pageFlagNext}&pageLastId=${blogBriefInfo[nextPageId-1].id}&pageNumber=${page.pageNumber+1}&artType=${artType}"
                   id="nextPage">下一页</a>
                <a href="">尾页</a>
            </div>
        </c:if>
        <c:if test="${empty blogBriefInfo}">
            <div class="panel panel-default">
                <div class="panel-footer">
                    暂无更多详情....
                </div>
            </div>
        </c:if>
    </div>
</div>

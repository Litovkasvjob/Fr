<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}" />
<div class="col-sm-3 col-md-2 sidebar absolute">
    <ul class="nav nav-sidebar">
        <li class="active"><time href="${base}/home">Users <span class="sr-only">(current)</span></time></li>
    </ul>
    <form action="friction" method="get">
        <table>
            <tr>
                <td>Enter number to calculate</td>
            </tr>
            <tr>
                <c:forEach var="forсe" items="${forсes}">
                    <li>${forсe}</li>
                   <input type="text" name="numbers">
                   <%--<input type="text" name="${forse}">--%>
                </c:forEach>
            </tr>
                <td>
                    <input type="submit" value="SEND">
                </td>
            </tr>
        </table>

    </form>
</div>